package assurance.contrat.controller;

import assurance.contrat.model.entities.Health;
import assurance.contrat.model.entities.Housing;
import assurance.contrat.model.entities.User;
import assurance.contrat.services.HealthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/health")
public class HealthController {

    private final HealthService healthService;

    @Autowired
    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/my-insurances")
    public String showUserHealthInsurances(Model model, HttpSession session) {
        Long loggedInUserId = getLoggedInUserId(session);
        if (loggedInUserId != null) {
            List<Health> userInsurances = healthService.getHealthInsurancesForUser(loggedInUserId);
            model.addAttribute("healthInsurances", userInsurances);
            return "quotes/health";
        } else {
            return "redirect:/login";
        }
    }

    private Long getLoggedInUserId(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    @GetMapping("/form")
    public String showHealthForm(Model model) {
        model.addAttribute("health", new Health());
        return "forms/health_insurance";
    }

    @PostMapping("/submit")
    public String submitHousingForm(@ModelAttribute Health health, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            health.setUser(user);
            double calculatedPrice = healthService.calculPrice(health);
            health.setPrice(calculatedPrice);
            healthService.save(health);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
}
