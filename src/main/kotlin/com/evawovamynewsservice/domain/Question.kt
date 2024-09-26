package com.evawovamynewsservice.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Comment

@Entity
class Question(
    formId: String,
    content: String,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("폼 아이디")
    @Column(nullable = false)
    var formId: String = formId
        protected set

    @Comment("질문 내용")
    @Column(nullable = false)
    var content: String = content
        protected set
}
