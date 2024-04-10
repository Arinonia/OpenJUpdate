package fr.arinonia.openjupdate.controller;

import fr.arinonia.openjupdate.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobController {

    private final JobService jobService;

    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs/create-job")
    public String createJob() {
        return "jobs/create-job";
    }
}
