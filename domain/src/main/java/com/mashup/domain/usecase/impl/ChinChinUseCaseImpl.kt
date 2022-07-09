package com.mashup.domain.usecase.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mashup.data.repository.ChinChinRepository
import com.mashup.domain.model.ChinChinModel
import com.mashup.domain.usecase.ChinChinUseCase
import javax.inject.Inject

class ChinChinUseCaseImpl @Inject constructor(private val chinChinRepository: ChinChinRepository) :
    ChinChinUseCase {
    override fun getTitle(): String {
        return chinChinRepository.getChinChins().value?.get(0)?.title ?: ""
    }

    override fun getChinChins(): LiveData<List<ChinChinModel>> {
       return Transformations.map(chinChinRepository.getChinChins()) { crimes->
           crimes.map{
               ChinChinModel(it.title, it.date)
           }
        }
    }


}