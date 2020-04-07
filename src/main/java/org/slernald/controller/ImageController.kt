package org.slernald.controller

import jdk.internal.util.xml.impl.Input
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.slernald.services.images.impl.ImageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayInputStream
import java.io.InputStream
import javax.servlet.http.HttpServletResponse

@RestController
class ImageController {

    @Autowired
    lateinit var imageService: ImageServiceImpl

    @GetMapping("images/{id}")
    fun getImage(@PathVariable id: Long, response: HttpServletResponse) {
        var image = imageService.getImage(id)

        image.ifPresent {
            // TODO: Handle nullables properly
            var bytes = ByteArray(it.data!!.size) { i -> it.data!![i] }
            response.contentType = "image/png"
            var inputStream = ByteArrayInputStream(bytes)
            IOUtils.copy(inputStream, response.outputStream)
        }
    }

    @GetMapping("/images")
    fun getAllImageIds(): List<Long?> {
        return imageService.getAllImageIds();
    }

}