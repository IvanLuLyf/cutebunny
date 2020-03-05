package cn.ivanlu.cutebunny.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping(path = {"/", "/index"})
    public String index(ModelMap modelMap,
                        HttpSession session) {
        String username = (String) session.getAttribute("username");
        modelMap.addAttribute("title", "首页");
        modelMap.addAttribute("username", username);
        return "index";
    }
}
