package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class SetKakaoFriendsPermissionUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(isAgree: Boolean) {
        loginRepository.setKakaoFriendsPermission(isAgree)
    }
}
