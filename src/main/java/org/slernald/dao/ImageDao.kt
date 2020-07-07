package org.slernald.dao

import javax.persistence.*

@Entity
class ImageDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @Lob
    lateinit var data: ByteArray

    @Lob
    lateinit var thumbData: ByteArray

    lateinit var contentType: String

    constructor() {}

    constructor(data: ByteArray, thumbData: ByteArray, contentType: String) {
        this.data = data
        this.thumbData = thumbData
        this.contentType = contentType
    }
}