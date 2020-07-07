package org.slernald.dto

class TagsDto {

    private lateinit var tags: List<Long>

    constructor() { }

    fun getTags(): List<Long> {
        return tags
    }

    fun setTags(tags: List<Long>) {
        this.tags = tags
    }

}