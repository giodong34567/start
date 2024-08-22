package springmvc.starter.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với Spring MVC v2 Starter!");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}