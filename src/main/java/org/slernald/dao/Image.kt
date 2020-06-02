package org.slernald.dao

import javax.persistence.*

@Entity
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @Lob
    var data: ByteArray? = null

    @Lob
    var thumbData: ByteArray? = null

    var contentType: String? = null

    constructor() {}

    constructor(data: ByteArray?, thumbData: ByteArray?, contentType: String?) {
        this.data = data
        this.thumbData = thumbData
        this.contentType = contentType
    }
}