package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.GroupRepository
import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class CreateNewGroupUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(): Boolean {
        val jwt: String = loginRepository.getJwt()

        return groupRepository.createNewGroup(
            jwt = " Bearer $jwt",
            groupName = "",
        )
    }
}
