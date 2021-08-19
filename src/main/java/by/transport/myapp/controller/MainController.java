package by.transport.myapp.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class MainController {

    private final MessageSource messageSource;

    public MainController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String main(Model model, Locale locale) {
        model.addAttribute("headerMessage", messageSource.getMessage("headerPT", null, locale));

        return "index";
    }
}
