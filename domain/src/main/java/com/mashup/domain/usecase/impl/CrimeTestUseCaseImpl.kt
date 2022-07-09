package com.mashup.domain.usecase.impl

import com.mashup.data.repository.CrimeRepository
import com.mashup.domain.model.CrimeModel
import com.mashup.domain.usecase.CrimeTestUseCase
import javax.inject.Inject

class CrimeTestUseCaseImpl @Inject constructor(private val crimeRepository: CrimeRepository) :
    CrimeTestUseCase {
    override fun getTitle(): String {
        return crimeRepository.getCrimes().value?.get(0)?.title ?: ""
    }

    override fun getCrimes(): List<CrimeModel> {
        return crimeRepository.getCrimes().value?.map {
            CrimeModel(it.title, it.date)
        } ?: listOf()

    }


}