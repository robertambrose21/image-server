package org.slernald.repository

import org.slernald.dao.ImageDao
import org.springframework.data.repository.CrudRepository

interface ImageRepository : CrudRepository<ImageDao?, Long?> {
}