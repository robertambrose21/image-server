package org.slernald.services.images.impl

import org.slernald.dao.Image
import org.slernald.repository.ImageRepository
import org.slernald.services.images.ImageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO
import org.imgscalr.Scalr;

@Service
class ImageServiceImpl() : ImageService {

    private val log = LoggerFactory.getLogger(ImageServiceImpl::class.java);

    @Autowired
    lateinit var imageRepository: ImageRepository;

    override fun saveImage(file: MultipartFile) {
        try {
            imageRepository.save(Image(file.bytes, createThumbnail(file, 200), file.contentType));
        } catch (e: IOException) {
            log.error("Failed to save the image", e);
        }
    }

    override fun getImage(id: Long): Optional<Image?> {
        return imageRepository.findById(id);
    }

    override fun getAllImageIds(): List<Long?> {
        return imageRepository.findAll().map { it -> it?.id };
    }

    private fun createThumbnail(file: MultipartFile, width: Int): ByteArray {
        var baos = ByteArrayOutputStream();
        var originalImage = ImageIO.read(file.inputStream);
        var thumbnailImage = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC,
                Scalr.Mode.AUTOMATIC, width, Scalr.OP_ANTIALIAS);

        ImageIO.write(thumbnailImage, file.contentType.split("/")[1], baos);
        return baos.toByteArray();
    }

}