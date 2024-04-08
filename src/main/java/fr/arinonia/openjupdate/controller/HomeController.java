package fr.arinonia.openjupdate.controller;

import fr.arinonia.openjupdate.models.Notification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage(Model model) {
        final List<Notification> notifications = Arrays.asList(
                new Notification("This is a general information send from the Java part! ✔️", Notification.NotificationType.INFO),
                new Notification("Success! Your operation was completed successfully. ✅", Notification.NotificationType.SUCCESS),
                new Notification("Warning ! Example", Notification.NotificationType.WARNING),
                new Notification("Ahem... I'm actually broken 😭", Notification.NotificationType.DANGER)
        );

        model.addAttribute("notifications", notifications);
        return "home";
    }
}
