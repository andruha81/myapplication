package by.transport.myapp.controller;

import by.transport.myapp.model.entity.User;
import by.transport.myapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final Logger logger = LogManager.getLogger(LoginController.class);

    private static final String REGISTRATION = "user/registration";

    public LoginController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
            logger.error("Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            logger.info("You have been logged out successfully.");
        }

        return "user/login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return REGISTRATION;
    }

    @PostMapping(value = "/registration")
    public String createNewUser(User user, Model model, BindingResult bindingResult) {
        User userExists = userService.loadUserByLogin(user.getLogin());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
            logger.error("There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            return REGISTRATION;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            logger.info("User has been registered successfully");
            model.addAttribute("user", new User());

        }
        return REGISTRATION;
    }
}
