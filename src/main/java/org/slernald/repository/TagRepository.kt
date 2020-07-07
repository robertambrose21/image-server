package org.slernald.repository

import org.slernald.dao.TagDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

// TODO: JPA Repo
@Component
interface TagRepository : CrudRepository<TagDao?, Long?> {
    fun findByName(name: String?): List<TagDao?>?
    fun findById(id: Long): TagDao?
    fun findByIdIn(ids: List<Long>): List<TagDao?>?
}