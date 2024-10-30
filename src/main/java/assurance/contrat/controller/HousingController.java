package assurance.contrat.controller;

import assurance.contrat.model.entities.Automobile;
import assurance.contrat.model.entities.Housing;
import assurance.contrat.model.entities.User;
import assurance.contrat.services.HousingService;
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
@RequestMapping("/housing")
public class HousingController {

    @Autowired
    private HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }

    @GetMapping("/my-insurances")
    public String showUserHousingInsurances(Model model, HttpSession session) {
        Long loggedInUserId = getLoggedInUserId(session);
        if (loggedInUserId != null) {
            List<Housing> userInsurances = housingService.getHousingInsurancesForUser(loggedInUserId);
            model.addAttribute("housingInsurances", userInsurances);
            return "quotes/housing";
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
    public String showHousingForm(Model model) {
        model.addAttribute("housing", new Housing());
        return "forms/housing_insurance";
    }

    @PostMapping("/submit")
    public String submitHousingForm(@ModelAttribute Housing housing, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            housing.setUser(user);
            double calculatedPrice = housingService.calculPrice(housing);
            housing.setPrice(calculatedPrice);

            housingService.save(housing);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }
}
