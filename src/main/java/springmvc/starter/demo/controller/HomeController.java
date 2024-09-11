package springmvc.starter.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home");
        return "page/home"; 
    }

    @GetMapping("/login")
    public String login() {
        return "page/auth/login";
    }
}