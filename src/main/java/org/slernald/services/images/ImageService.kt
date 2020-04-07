package org.slernald.services.images

import org.slernald.dao.Image
import org.springframework.web.multipart.MultipartFile
import java.util.*

interface ImageService {

    fun saveImage(file: MultipartFile)

    fun getImage(id: Long): Optional<Image?>

    fun getAllImageIds(): List<Long?>

}