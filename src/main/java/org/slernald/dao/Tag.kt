package org.slernald.dao

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    var name: String? = null
        private set
    var count: Int? = null
        private set

    constructor() {}

    constructor(name: String?, count: Int?) {
        this.name = name
        this.count = count
    }

    override fun toString(): String {
        return "Tag[id=$id, name=$name, count=$count]"
    }
}