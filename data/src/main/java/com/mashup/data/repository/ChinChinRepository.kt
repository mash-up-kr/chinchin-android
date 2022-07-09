package com.mashup.data.repository

import androidx.lifecycle.LiveData
import com.mashup.data.dto.local.ChinChin
import java.util.*

interface ChinChinRepository {
    fun getChinChins(): LiveData<List<ChinChin>>
    fun getChinChin(id: UUID): LiveData<ChinChin?>
}