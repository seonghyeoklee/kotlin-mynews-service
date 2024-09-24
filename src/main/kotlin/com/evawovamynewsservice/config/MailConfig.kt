package com.evawovamynewsservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class MailConfig {
    @Value("\${spring.mail.host}")
    lateinit var host: String

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Value("\${spring.mail.password}")
    lateinit var password: String

    @Value("\${spring.mail.port}")
    lateinit var port: String

    @Bean
    fun javaMailService(): JavaMailSender {
        val javaMailSender = JavaMailSenderImpl()
        javaMailSender.host = host
        javaMailSender.username = username
        javaMailSender.password = password
        javaMailSender.port = port.toInt()
        javaMailSender.javaMailProperties = getMailProperties()
        return javaMailSender
    }

    fun getMailProperties(): Properties {
        val properties = Properties()
        properties.setProperty("mail.transport.protocol", "smtp")
        properties.setProperty("mail.smtp.auth", "true")
        properties.setProperty("mail.smtp.starttls.enable", "true")
        properties.setProperty("mail.debug", "true")
        properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com")
        properties.setProperty("mail.smtp.ssl.enable", "true")
        return properties
    }
}
