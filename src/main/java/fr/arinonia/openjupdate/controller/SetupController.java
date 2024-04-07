package fr.arinonia.openjupdate.controller;

import fr.arinonia.openjupdate.models.SetupCheck;
import fr.arinonia.openjupdate.service.SetupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class SetupController {

    private final SetupService setupService;

    public SetupController(final SetupService setupService) {
        this.setupService = setupService;
    }

    @GetMapping("/setup")
    public String setupPage(Model model) {
        final List<SetupCheck> checks = setupService.performSetupChecks();
        model.addAttribute("checks", checks);

        boolean allChecksPassed = checks.stream().allMatch(SetupCheck::isSuccess);
        model.addAttribute("allChecksPassed", allChecksPassed);

        return "setup";
    }
}
