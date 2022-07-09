package com.mashup.domain.usecase

import androidx.lifecycle.LiveData
import com.mashup.domain.model.ChinChinModel

interface ChinChinUseCase {
    fun getTitle(): String
    fun getChinChins(): LiveData<List<ChinChinModel>>
}