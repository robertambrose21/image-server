package org.slernald.controller

import org.apache.tomcat.util.http.fileupload.IOUtils
import org.slernald.dto.TagsDto
import org.slernald.services.images.impl.ImageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import javax.servlet.http.HttpServletResponse

@RestController
class ImageController {

    @Autowired
    lateinit var imageService: ImageServiceImpl

    @GetMapping("images/{id}")
    @ResponseBody
    fun getImage(@PathVariable id: Long, response: HttpServletResponse) {
        var image = imageService.getImage(id)

        image.ifPresent {
            response.contentType = it.contentType
            var inputStream = ByteArrayInputStream(it.data)
            IOUtils.copy(inputStream, response.outputStream)
        }
    }

    @GetMapping("images/{id}/thumb")
    fun getImageThumbnail(@PathVariable id: Long, response: HttpServletResponse) {
        var image = imageService.getImage(id)

        image.ifPresent {
            response.contentType = it.contentType
            var inputStream = ByteArrayInputStream(it.thumbData)
            IOUtils.copy(inputStream, response.outputStream)
        }
    }

    @GetMapping("/images")
    fun getAllImageIds(): List<Long?> {
        return imageService.getAllImageIds()
    }

    @PostMapping("/images")
    fun saveImage(@RequestParam("data") file: MultipartFile) {
        imageService.saveImage(file, emptyList())
    }

    @PutMapping("images/{id}/tags")
    fun addImageTags(@PathVariable id: Long, @RequestBody dto: TagsDto) {
        imageService.addTagsToImage(id, dto.getTags())
    }

    @GetMapping("images/{id}/tags")
    fun getImageTags(@PathVariable id: Long): List<String?>? {
        return imageService.getImageTagNames(id)
    }

}