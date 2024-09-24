package com.evawovamynewsservice.service.dto

import com.evawovamynewsservice.domain.News
import java.time.LocalDateTime

data class NewsDto(
    val id: Long,
    val title: String,
    val link: String,
    val searchKeyword: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun News.toDto() =
    NewsDto(
        id = id,
        title = title,
        link = link,
        searchKeyword = searchKeyword,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
