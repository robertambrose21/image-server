package org.slernald.services.images

import org.slernald.dao.ImageDao
import org.springframework.web.multipart.MultipartFile
import java.util.*

interface ImageService {

    fun saveImage(file: MultipartFile, tags: List<Long>)

    fun getImage(id: Long): Optional<ImageDao?>

    fun getAllImageIds(): List<Long?>

    fun addTagsToImage(imageId: Long, tags: List<Long>)

    fun getImageTagNames(imageId: Long): List<String?>?

}