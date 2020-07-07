package org.slernald.services.tags

import org.slernald.dao.TagDao

interface TagService {

    fun saveTag(name: String)

    fun getTagById(id: Long): TagDao?

    fun getTagByName(name: String): TagDao?

}