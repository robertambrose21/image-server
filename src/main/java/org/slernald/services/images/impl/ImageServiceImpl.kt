package org.slernald.services.images.impl

import org.slernald.dao.Image
import org.slernald.repository.ImageRepository
import org.slernald.services.images.ImageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class ImageServiceImpl() : ImageService {

    private val log = LoggerFactory.getLogger(ImageServiceImpl::class.java);

    @Autowired
    lateinit var imageRepository: ImageRepository;

    override fun saveImage(file: MultipartFile) {
        try {
            var bytes = Array<Byte>(file.bytes.size) { i -> file.bytes[i] }
            imageRepository.save(Image(bytes));
        } catch(e: IOException) {
            log.error("Failed to save the image", e);
        }
    }

    override fun getImage(id: Long): Optional<Image?> {
        return imageRepository.findById(id);
    }

    override fun getAllImageIds(): List<Long?> {
        return imageRepository.findAll().map { it -> it?.id };
    }

}