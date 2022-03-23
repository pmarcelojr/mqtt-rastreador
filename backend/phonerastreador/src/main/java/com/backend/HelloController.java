package com.backend;

import com.backend.service.MqttSecurity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    @RequestMapping("/password/{password}")
    public String generatePassword(@PathVariable String password) {
        return new MqttSecurity().createPassword(password);
    }
}
