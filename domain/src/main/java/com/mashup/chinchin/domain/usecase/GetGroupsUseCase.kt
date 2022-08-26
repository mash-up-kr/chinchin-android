package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Groups
import com.mashup.chinchin.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(): Groups = groupRepository.getGroups()
}
