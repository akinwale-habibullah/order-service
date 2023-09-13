package com.akinwalehabib.orderservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akinwalehabib.orderservice.config.PolarProperties;

@RestController
public class HomeController {
    private PolarProperties polarProperties;

    public HomeController(PolarProperties polarProperties) {
      this.polarProperties = polarProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return polarProperties.getGreeting();
    }
}
