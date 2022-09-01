package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.local.LocalCommonDataSource
import com.mashup.chinchin.domain.repository.CommonRepository
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val localCommonDataSource: LocalCommonDataSource,
): CommonRepository {
    override fun setIsFirstEnter(isFirstEnter: Boolean) {
        localCommonDataSource.setIsFirstEnter(isFirstEnter)
    }

    override fun getIsFirstEnter(): Boolean = localCommonDataSource.getIsFirstEnter()
}
