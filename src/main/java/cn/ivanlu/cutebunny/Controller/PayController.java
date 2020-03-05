package cn.ivanlu.cutebunny.Controller;

import cn.ivanlu.cutebunny.Util.BlockChainUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PayController {
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(ModelMap modelMap,
                      HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            String address = BlockChainUtil.getUser(username).getAddress();
            String balance = BlockChainUtil.getBalance(address);
            modelMap.addAttribute("walletAddr", address);
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("balance", balance);
            modelMap.addAttribute("title", "我的账户");
            return "pay";
        } else {
            return "redirect:/user/login?referrer=/pay";
        }
    }

    @RequestMapping(value = "/pay/plus", method = RequestMethod.GET)
    public String payPlus(ModelMap modelMap,
                          HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            String address = BlockChainUtil.getUser(username).getAddress();
            if (BlockChainUtil.payTo(address)) {
                modelMap.addAttribute("message", "充值成功");
            } else {
                modelMap.addAttribute("message", "充值失败");
            }
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "充值");
            return "pay_plus";
        } else {
            return "redirect:/user/login?referrer=/pay/plus";
        }
    }
}
