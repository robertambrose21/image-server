package org.slernald.dao

import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["imageId", "tagId"])])
class ImageTagDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    var imageId: Long = 0

    var tagId: Long = 0

    constructor() { }

    constructor(imageId: Long, tagId: Long) {
        this.imageId = imageId
        this.tagId = tagId
    }

}