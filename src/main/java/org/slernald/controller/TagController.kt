package org.slernald.controller

import org.slernald.dao.Tag
import org.slernald.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TagController {

    @Autowired
    lateinit var tagRepository: TagRepository

    @GetMapping("/tags")
    fun getTags(): List<Tag?> {
        return tagRepository.findAll().toList()
    }

}