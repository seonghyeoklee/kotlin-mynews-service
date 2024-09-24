package com.evawovamynewsservice.controller

import com.evawovamynewsservice.service.EmailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController(
    private val emailService: EmailService,
) {
    @PostMapping("/emails")
    fun sendEmail() {
        emailService.sendEmail("dltjdgur327@gmail.com", "테스트 이메일")
    }
}
