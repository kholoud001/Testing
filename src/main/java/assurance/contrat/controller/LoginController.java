package assurance.contrat.controller;

import assurance.contrat.model.entities.User;
import assurance.contrat.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private  UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView showLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView loginUser(@ModelAttribute User user, HttpSession session) {
        User authenticatedUser = userService.authenticate(user.getEmail(), user.getPassword());

        if (authenticatedUser != null) {
            session.setAttribute("loggedInUser", authenticatedUser);

            ModelAndView modelAndView = new ModelAndView("home");
            modelAndView.addObject("user", authenticatedUser);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
    }

}
