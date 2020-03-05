package cn.ivanlu.cutebunny.Controller;

import cn.ivanlu.cutebunny.Service.UserService;
import cn.ivanlu.cutebunny.Util.BlockChainUtil;
import cn.ivanlu.cutebunny.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.crypto.Credentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user/account", method = RequestMethod.GET)
    public String account(HttpSession session, ModelMap modelMap) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            modelMap.addAttribute("username", username);
            modelMap.addAttribute("title", "我的账户");
            modelMap.addAttribute("walletAddr", BlockChainUtil.getUser(username).getAddress());
            return "user_account";
        } else {
            return "redirect:/user/login?referrer=/user/account";
        }
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.GET)
    public String login(ModelMap modelMap,
                        @RequestParam(value = "referrer", required = false) String referrer) {
        modelMap.addAttribute("title", "登录");
        modelMap.addAttribute("referrer", referrer);
        return "login";
    }

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        HttpSession session,
                        ModelMap modelMap,
                        @RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(value = "referrer", required = false) String referrer) {
        String path = "user/";
        Credentials credentials = BlockChainUtil.login(path, username, password);
        if (credentials != null) {
            session.setAttribute("username", username);
            if (referrer != null) {
                return "redirect:" + referrer;
            } else {
                return "redirect:/index";
            }
        } else {
            modelMap.addAttribute("title", "登录");
            modelMap.addAttribute("err_msg", "登录失败");
            modelMap.addAttribute("referrer", referrer);
        }
        return "login";
    }

    @RequestMapping(path = "/user/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("title", "注册");
        return "register";
    }

    @RequestMapping(path = "/user/register", method = RequestMethod.POST)
    public String register(ModelMap modelMap,
                           HttpSession session,
                           @RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        String path = "user/";
        Credentials credentials = BlockChainUtil.register(path, username, password);
        if (credentials != null) {
            int r = userService.register(username, email, StringUtil.md5(password));
            if (r > 0) {
                session.setAttribute("username", username);
                return "redirect:/index";
            } else {
                modelMap.addAttribute("err_msg", "注册失败");
            }
        } else {
            modelMap.addAttribute("err_msg", "注册失败");
        }
        modelMap.addAttribute("title", "注册");
        return "register";
    }

    @RequestMapping(path = "/user/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/index";
    }
}
