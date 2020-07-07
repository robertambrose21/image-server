package org.slernald.dao

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class TagDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    var name: String? = null
        private set

    constructor() {}

    constructor(name: String?) {
        this.name = name
    }

    override fun toString(): String {
        return "Tag[id=$id, name=$name]"
    }
}