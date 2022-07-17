package com.mashup.domain.usecase.impl

import com.mashup.data.dto.local.ChinChin
import com.mashup.data.repository.ChinChinRepository
import com.mashup.domain.model.ChinChinModel
import com.mashup.domain.usecase.ChinChinUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChinChinUseCaseImpl @Inject constructor(private val chinChinRepository: ChinChinRepository) :
    ChinChinUseCase {

    override fun getChinChins(): Flow<List<ChinChinModel>> {
        return chinChinRepository.getChinChins().map { chinchins ->
            chinchins.map { chinchin ->
                ChinChinModel(
                    uid = chinchin.uid,
                    name = chinchin.name,
                    date = chinchin.date,
                    isFriend = chinchin.isFriend,
                )
            }

        }
    }

    override suspend fun addChinChin(name: String) {
        chinChinRepository.addChinChin(ChinChin(name = name))
    }

    override suspend fun deleteChinChin(chinchinModel: ChinChinModel) {
        chinChinRepository.deleteChinChin(chinchinModel.toChinchin())
    }

    override suspend fun updateChinChin(chinchinModel: ChinChinModel) {
        chinChinRepository.updateChinChin(chinchinModel.toChinchin())
    }


}