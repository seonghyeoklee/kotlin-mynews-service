package com.evawovamynewsservice.service

import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class GamilSendService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine,
) : EmailService {
    @Async
    override fun sendEmail(
        to: String,
        subject: String,
    ) {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        // TODO 이메일 템플릿 수정
        val context = Context()
        context.setVariable("name", "홍길동")
        context.setVariable("newsTitle", "오늘의 뉴스")

        val htmlContent = templateEngine.process("emailTemplate.html", context)

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)

        javaMailSender.send(message)
    }
}
