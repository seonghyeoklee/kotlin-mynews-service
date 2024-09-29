package com.evawovamynewsservice.repository

import com.evawovamynewsservice.domain.Form
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FormRepository : JpaRepository<Form, Long>
