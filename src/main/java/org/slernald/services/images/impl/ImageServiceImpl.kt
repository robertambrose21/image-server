package org.slernald.services.images.impl

import org.slernald.dao.ImageDao
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
import org.slernald.dao.ImageTagDao
import org.slernald.repository.ImageTagRepository
import org.slernald.repository.TagRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import kotlin.streams.toList

@Service
class ImageServiceImpl() : ImageService {

    private val log = LoggerFactory.getLogger(ImageServiceImpl::class.java)

    @Autowired
    lateinit var imageRepository: ImageRepository

    @Autowired
    lateinit var imageTagRepository: ImageTagRepository

    @Autowired
    lateinit var tagRepository: TagRepository

    override fun saveImage(file: MultipartFile, tags: List<Long>) {
        try {
            val image = imageRepository.save(ImageDao(file.bytes, createThumbnail(file, 200), file.contentType))

            if(tags.isNotEmpty()) {
                image.id?.let { addTagsToImage(it, tags) }
            }
        } catch (e: IOException) {
            log.error("Failed to save the image", e)
        }
    }

    override fun getImage(id: Long): Optional<ImageDao?> {
        return imageRepository.findById(id)
    }

    override fun getAllImageIds(): List<Long?> {
        return imageRepository.findAll().map { it?.id }
    }

    override fun addTagsToImage(imageId: Long, tags: List<Long>) {
        val image = getImage(imageId)

        if(!image.isPresent) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Image with id '$imageId' does not exist")
        }

        try {
            imageTagRepository.saveAll(tags.stream().map { ImageTagDao(imageId, it) }.toList())
        } catch(e: DataIntegrityViolationException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Request contains tags which already exist for image '$imageId'")
        }
    }

    override fun getImageTagNames(imageId: Long): List<String?>? {
        val imageTagIds = imageTagRepository.findByImageId(imageId)?.map { it?.tagId }
        return tagRepository.findByIdIn(imageTagIds)?.map { it?.name }
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