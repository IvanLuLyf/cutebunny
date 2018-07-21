package com.twimi.cutebunny.Controller;

import com.twimi.cutebunny.Model.FileModel;
import com.twimi.cutebunny.Service.FileService;
import com.twimi.cutebunny.Util.BlockChainUtil;
import com.twimi.cutebunny.Util.IPFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(path = "/file/check", method = RequestMethod.GET)
    public String check(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        modelMap.addAttribute("username", username);
        modelMap.addAttribute("title", "文件比较");
        return "file_check";
    }

    @RequestMapping(path = "/file/compare/{id}", method = RequestMethod.GET)
    public String compare(HttpSession session, ModelMap modelMap, @PathVariable("id") int id) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            FileModel fileModel = fileService.getFileById(id);
            if (fileModel != null && username.equals(fileModel.getUsername())) {
                modelMap.addAttribute("username", username);
                modelMap.addAttribute("filename", fileModel.getFilename());
                modelMap.addAttribute("filehash", fileModel.getFilehash());
                modelMap.addAttribute("title", "文件比较");
                return "file_compare";
            } else {
                return "redirect:/user/login?referrer=/file/compare/" + id;
            }
        } else {
            return "redirect:/user/login?referrer=/file/compare/" + id;
        }
    }

    @RequestMapping(path = "/file/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkAction(@RequestParam("file") MultipartFile file) {
        try {
            return IPFSUtil.uploadFile(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(path = "/file/upload", method = RequestMethod.GET)
    public String upload(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "上传文件");
            return "file_upload";
        } else {
            return "redirect:/user/login?referrer=/file/upload";
        }
    }

    @RequestMapping(path = "/file/upload", method = RequestMethod.POST)
    public @ResponseBody
    FileModel uploadAction(@RequestParam("file") MultipartFile file, @RequestParam(name = "filename", required = false) String filename, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            String name = filename != null ? filename : file.getOriginalFilename();
            try {
                String hash = IPFSUtil.uploadFile(file.getBytes());
                TransactionReceipt receipt = BlockChainUtil.getContract(username).saveFile(name, hash).send();
                FileModel model = new FileModel(username, name, hash, receipt.getTransactionHash());
                fileService.saveFile(model);
                return model;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/file/list")
    public String fileList(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<FileModel> fileModels = fileService.getFilesByUsername(username);
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "文件列表");
            modelMap.addAttribute("files", fileModels);
            return "file_list";
        } else {
            return "redirect:/user/login?referrer=/file/list";
        }
    }
}
