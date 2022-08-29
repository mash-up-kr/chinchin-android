package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class GetKakaoFriendsPermissionUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(): Boolean {
        return loginRepository.getKakaoFriendsPermission()
    }
}
