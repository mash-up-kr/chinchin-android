package com.mashup.domain.usecase

import com.mashup.domain.model.CrimeModel

interface CrimeTestUseCase {
    fun getTitle(): String
    fun getCrimes(): List<CrimeModel>
}