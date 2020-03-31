package org.slernald.dao

import javax.persistence.*

@Entity
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @Lob
    var data: Array<Byte>? = null

    constructor() {}

    constructor(data: Array<Byte>?) {
        this.data = data
    }
}