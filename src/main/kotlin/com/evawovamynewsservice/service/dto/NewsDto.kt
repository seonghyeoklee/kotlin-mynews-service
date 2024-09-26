package com.evawovamynewsservice.service.dto

import com.evawovamynewsservice.domain.News
import java.time.LocalDateTime

data class NewsDto(
    val id: Long,
    val title: String,
    val link: String,
    val originalLink: String,
    val description: String,
    val pubDate: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun News.toDto() =
    NewsDto(
        id = id,
        title = title,
        link = link,
        originalLink = originalLink,
        description = description,
        pubDate = pubDate,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
