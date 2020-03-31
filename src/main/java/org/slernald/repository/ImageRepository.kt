package org.slernald.repository

import org.slernald.dao.Image
import org.springframework.data.repository.CrudRepository

interface ImageRepository : CrudRepository<Image?, Long?> {
}