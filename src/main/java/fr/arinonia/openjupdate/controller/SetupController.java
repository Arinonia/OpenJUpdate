package fr.arinonia.openjupdate.controller;

import fr.arinonia.openjupdate.models.SetupCheck;
import fr.arinonia.openjupdate.response.Response;
import fr.arinonia.openjupdate.service.SetupService;
import fr.arinonia.openjupdate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class SetupController {

    private final SetupService setupService;
    private final UserService userService;

    public SetupController(final SetupService setupService, final UserService userService) {
        this.setupService = setupService;
        this.userService = userService;
    }

    @GetMapping("/setup")
    public String setupPage(final Model model) {
        final List<SetupCheck> checks = setupService.performSetupChecks();
        model.addAttribute("checks", checks);

        boolean allChecksPassed = checks.stream().allMatch(SetupCheck::isSuccess);
        model.addAttribute("allChecksPassed", allChecksPassed);

        return "setup";
    }

    @PostMapping("/setup/admin")
    public String registerAdmin(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) { // Use Model instead of ModelAndView
        final Response response = this.userService.registerUser(username, password, confirmPassword);

        if (response.isSuccess()) {
            model.addAttribute("successMessage", "Admin account has been successfully created. You will be redirect soon");
        } else {
            model.addAttribute("errorMessage", response.getErrorMessage());
        }

        return setupPage(model);
    }

}
