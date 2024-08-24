package springmvc.starter.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Map<String, Object> errors = errorAttributes.getErrorAttributes((WebRequest) request, ErrorAttributeOptions.defaults());
        model.addAttribute("status", errors.get("status"));
        model.addAttribute("error", errors.get("error"));
        model.addAttribute("message", errors.get("message"));
        model.addAttribute("path", errors.get("path"));

        Integer statusCode = (Integer) errors.get("status");
        if (statusCode == 404) {
            return "page/error/404";
        } else if (statusCode == 500) {
            return "page/error/404";
        }
        return "page/error/404";
    }
}
