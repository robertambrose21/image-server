package org.slernald.repository

import org.slernald.dao.Tag
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

// TODO: JPA Repo
@Component
interface TagRepository : CrudRepository<Tag?, Long?> {
    fun findByName(name: String?): List<Tag?>?
    fun findById(id: Long): Tag?
}