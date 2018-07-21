package com.twimi.cutebunny.Controller;

import com.twimi.cutebunny.Model.IdCardModel;
import com.twimi.cutebunny.Model.ResultModel;
import com.twimi.cutebunny.Util.BaiduUtil;
import com.twimi.cutebunny.Util.BlockChainUtil;
import com.twimi.cutebunny.Util.CodeUtil;
import com.twimi.cutebunny.Util.IPFSUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.servlet.http.HttpSession;

@Controller
public class IdCardController {
    @RequestMapping(path = "/idcard/view")
    public String idcard_view(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            modelMap.addAttribute("username", username);
            try {
                String data = BlockChainUtil.getContract(username).getData("id_card").send();
                if ("null".equals(data)) {
                    modelMap.addAttribute("hasIdCard", false);
                } else {
                    data = CodeUtil.decode(data, "CuteBunny");
                    JSONObject except = new JSONObject(data);
                    IdCardModel idCardModel = new IdCardModel(except);
                    modelMap.addAttribute("idcard", idCardModel);
                    modelMap.addAttribute("hasIdCard", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                modelMap.addAttribute("hasIdCard", false);
            }
            modelMap.addAttribute("title", "我的身份证");
            return "idcard_view";
        } else {
            return "redirect:/user/login?referrer=/idcard/view";
        }
    }

    @RequestMapping(path = "/idcard/check", method = RequestMethod.GET)
    public String idcardCheck(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "身份证校验");
            return "idcard_check";
        } else {
            return "redirect:/user/login?referrer=/idcard/check";
        }
    }

    @RequestMapping(path = "/idcard/check", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel idcardCheckAction(@RequestParam("file") MultipartFile file, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            try {
                IdCardModel actual = BaiduUtil.processIdcard(file.getBytes());
                if (actual != null) {
                    String data = BlockChainUtil.getContract(username).queryData("idcard_" + actual.getId()).send();
                    if ("null".equals(data)) {
                        return new ResultModel("系统未存储当前身份证");
                    } else {
                        data = CodeUtil.decode(data, "CuteBunny");
                        JSONObject exceptJSON = new JSONObject(data);
                        IdCardModel expect = new IdCardModel(exceptJSON);
                        return new ResultModel(expect, actual);
                    }
                } else {
                    return new ResultModel("无法识别");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultModel("未知错误");
            }
        } else {
            return new ResultModel("没有登陆");
        }
    }

    @RequestMapping(path = "/idcard/upload", method = RequestMethod.GET)
    public String idcard(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "身份证上传");
            return "idcard_upload";
        } else {
            return "redirect:/user/login?referrer=/idcard/upload";
        }
    }

    @RequestMapping(path = "/idcard/upload", method = RequestMethod.POST)
    @ResponseBody
    public IdCardModel uploadIdcard(@RequestParam("file") MultipartFile file, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            try {
                IdCardModel model = BaiduUtil.processIdcard(file.getBytes());
                String hash = IPFSUtil.uploadFile(file.getBytes());
                if (model != null) {
                    model.setHash(hash);
                    String data = CodeUtil.encode(model.toJSON(), "CuteBunny");
                    TransactionReceipt receipt = BlockChainUtil
                            .getContract(username)
                            .saveData("idcard_" + model.getId(), "id_card", data)
                            .send();
                    model.setBlockHash(receipt.getTransactionHash());
                }
                return model;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
