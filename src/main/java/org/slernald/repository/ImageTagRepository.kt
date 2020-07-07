package org.slernald.repository

import org.slernald.dao.ImageTagDao
import org.springframework.data.repository.CrudRepository

interface ImageTagRepository : CrudRepository<ImageTagDao?, Long?> {
    fun findByImageId(imageId: Long): List<ImageTagDao>
}