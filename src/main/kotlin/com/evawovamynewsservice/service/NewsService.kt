package com.evawovamynewsservice.service

import com.evawovamynewsservice.repository.NewsRepository
import com.evawovamynewsservice.service.dto.NewsDto
import com.evawovamynewsservice.service.dto.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NewsService(
    private val newsRepository: NewsRepository,
) {
    fun getPageNews(pageable: Pageable): Page<NewsDto> = newsRepository.findPageBy(pageable).map { it.toDto() }
}
