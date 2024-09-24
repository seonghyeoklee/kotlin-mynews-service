package com.evawovamynewsservice.service

interface EmailService {
    fun sendEmail(
        to: String,
        subject: String,
    )
}
