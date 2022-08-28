package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.GroupDetail
import com.mashup.chinchin.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupDetailUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(groupId: Long): GroupDetail =
        groupRepository.getGroupDetail(groupId)
}