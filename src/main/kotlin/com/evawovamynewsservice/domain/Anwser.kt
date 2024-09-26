package com.evawovamynewsservice.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
class Anwser(
    question: Question,
    email: String,
    content: String,
) : BaseEntity() {
    @Comment("PK")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("질문")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    var question: Question = question
        protected set

    @Comment("응답자")
    @Column(nullable = false)
    var email: String = email
        protected set

    @Comment("응답 내용")
    @Column(nullable = false)
    var content: String = content
        protected set
}
