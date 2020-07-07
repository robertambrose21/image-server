package org.slernald.services.tags.impl

import org.slernald.dao.TagDao
import org.slernald.repository.TagRepository
import org.slernald.services.tags.TagService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(): TagService {

    private val log = LoggerFactory.getLogger(TagServiceImpl::class.java);

    @Autowired
    lateinit var tagRepository: TagRepository;

    override fun saveTag(name: String) {
        tagRepository.save(TagDao(name))
    }

    override fun getTagById(id: Long): TagDao? {
        return tagRepository.findById(id);
    }

    override fun getTagByName(name: String): TagDao? {
        return tagRepository.findByName(name)?.first()
    }

}