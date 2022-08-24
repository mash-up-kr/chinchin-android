package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Groups
import com.mashup.chinchin.domain.repository.GroupRepository
import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(): Groups {
        val jwt: String = loginRepository.getJwt()

        return groupRepository.getGroups(jwt = " Bearer $jwt")
    }
}
