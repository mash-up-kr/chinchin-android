package com.mashup.chinchin.domain.repository

interface CommonRepository {
    fun setIsFirstEnter(isFirstEnter: Boolean)
    fun getIsFirstEnter(): Boolean
}
