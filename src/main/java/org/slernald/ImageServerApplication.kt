package org.slernald

import org.slernald.dao.Tag
import org.slernald.repository.ImageRepository
import org.slernald.repository.TagRepository
import org.slernald.services.images.impl.ImageServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@SpringBootApplication
open class ImageServerApplication {
    @Autowired
    lateinit var imageService: ImageServiceImpl

    private fun createTestImage(): MultipartFile? {
        var path = Paths.get("assets/icedout.png")
        return try {
            MockMultipartFile(
                    "icedout.png",
                    "icedout.png",
                    "image/png",
                    Files.readAllBytes(path));
        } catch(e: IOException) {
            log.error("Couldn't find image", e)
            null
        }
    }

    @Bean
    open fun demo(tagRepository: TagRepository, imageRepository: ImageRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            tagRepository.save(Tag("TestTag5", 2))
            tagRepository.save(Tag("TestTag2", 1))
            tagRepository.save(Tag("Ocean", 50))

            log.info(System.getProperty("user.dir"))

            createTestImage()?.let { imageService.saveImage(it) }

            for(tag in tagRepository.findAll()) {
                log.info(tag.toString())
            }

            for(image in imageRepository.findAll()) {
                log.info(image?.id.toString())
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(ImageServerApplication::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ImageServerApplication::class.java, *args)
        }
    }
}