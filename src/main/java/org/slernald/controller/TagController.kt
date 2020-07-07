package org.slernald.controller

import org.slernald.dao.TagDao
import org.slernald.services.tags.impl.TagServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TagController {

    @Autowired
    lateinit var tagService: TagServiceImpl

    @GetMapping("/tags/{name}")
    fun findTag(@PathVariable name: String): TagDao? {
        return tagService.getTagByName(name)
    }
}