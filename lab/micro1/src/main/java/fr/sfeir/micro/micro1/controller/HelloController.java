package fr.sfeir.micro.micro1.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;


@RequestMapping("/hello")
@RestController
// @RequiredArgsConstructor
public class HelloController {
    @Lazy
    @Autowired(required = false)
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public String hello() {

        return "Bonjour de la part de '%s'".formatted(eurekaClient.getApplication(appName)
                .getName());
    }

    @GetMapping("/server")
    public String server(HttpServletRequest request) {

        return "Request on '%s'!".formatted(request.getLocalAddr());
    }
}
