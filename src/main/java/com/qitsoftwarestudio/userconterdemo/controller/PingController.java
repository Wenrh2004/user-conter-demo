package com.qitsoftwarestudio.userconterdemo.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.representer.BaseRepresenter;

@RestController
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return String.format("ping");
    }

}
