package net.javaguides.springboot.microservice.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Employee Service - MessageController",
        description = "Message Controller exposes REST APIs for Employee-Service"
)@RefreshScope
@RestController
public class MessageController {

    @Value("${sprint.boot.message}")
    private String message;

    @Operation(
            summary = "Get Message REST API",
            description = "Get Message REST API is used to get message string"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("message")
    public String getMessage(){
        return message;
    }
}
