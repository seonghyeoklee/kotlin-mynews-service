package com.evawovamynewsservice.service

import com.evawovamynewsservice.domain.News
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class GamilSendService(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine,
) : EmailService {
    @Async
    override fun sendEmail(
        to: String,
        subject: String,
        newsList: List<News>,
    ) {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        val context = Context()
        context.setVariable("newsList", newsList)
        context.setVariable(
            "updateDate",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        )

        val htmlContent = templateEngine.process("emailTemplate.html", context)

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)

        javaMailSender.send(message)
    }
}
