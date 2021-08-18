package by.transport.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class MainController {

    @Autowired
    @Qualifier("messageResourceSB")
    MessageSource messageSource;

    @GetMapping("/")
    public String main(Model model, Locale locale) {
        model.addAttribute("headerMessage", messageSource.getMessage("headerPT", null, locale));
        return "index";
    }
}
