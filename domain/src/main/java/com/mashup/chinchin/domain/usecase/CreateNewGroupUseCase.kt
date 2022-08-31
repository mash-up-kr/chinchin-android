package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.GroupRepository
import javax.inject.Inject

class CreateNewGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(groupName: String): Boolean {
        return groupRepository.createNewGroup(
            groupName = groupName,
        )
    }
}
