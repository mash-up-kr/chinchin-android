package com.mashup.chinchin.domain.base

interface DomainMapper<T : DomainModel?> {

    fun toDomainModel(): T
}