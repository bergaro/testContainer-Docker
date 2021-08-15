package ru.netology.springBootApp.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
    private SystemProfile systemProfile;

    public AppController(SystemProfile systemProfile) {
        this.systemProfile = systemProfile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return systemProfile.getProfile();
    }
}
