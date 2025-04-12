package com.cehn17.jobportal.controller;

import com.cehn17.jobportal.entity.JobPostActivity;
import com.cehn17.jobportal.services.JobPostActivityService;
import com.cehn17.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UserService userService;

    @Autowired
    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UserService usersService) {
        this.jobPostActivityService = jobPostActivityService;
        this.userService = usersService;
    }

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobDetails = jobPostActivityService.getOne(id);

        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user", userService.getCurrentUserProfile());
        return "job-details";
    }
}
