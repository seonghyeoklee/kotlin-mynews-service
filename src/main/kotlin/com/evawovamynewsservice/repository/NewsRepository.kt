package com.evawovamynewsservice.repository

import com.evawovamynewsservice.domain.News
import com.evawovamynewsservice.domain.QNews.news
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository :
    JpaRepository<News, Long>,
    CustomNewsRepository

interface CustomNewsRepository {
    fun findPageBy(pageable: Pageable): Page<News>
}

class CustomNewsRepositoryImpl :
    QuerydslRepositorySupport(News::class.java),
    CustomNewsRepository {
    override fun findPageBy(pageable: Pageable): Page<News> {
        val result =
            from(news)
                .orderBy(news.id.desc())
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .fetchResults()

        return PageImpl(result.results, pageable, result.total)
    }
}
