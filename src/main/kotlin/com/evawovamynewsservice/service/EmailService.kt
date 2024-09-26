package com.evawovamynewsservice.service

import com.evawovamynewsservice.domain.News

interface EmailService {
    fun sendEmail(
        to: String,
        subject: String,
        newsList: List<News>,
    )
}
