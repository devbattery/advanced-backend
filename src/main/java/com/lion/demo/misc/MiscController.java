package com.lion.demo.misc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/misc")
@RequiredArgsConstructor
public class MiscController {

    private final ApiService apiService;
    private final MetricsService metricsService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/api")
    public String api() {
        return apiService.fetchData();
    }

    @GetMapping("/port")
    public String port() {
        return "Server port = " + serverPort;
    }

    @GetMapping("/record-metrics")
    public String recordMetrics() {
        metricsService.recordCustomMetrics();
        return "Custom metrics recorded!";
    }

}
