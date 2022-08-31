package com.mashup.chinchin.presenter.friend_detail.model

import com.mashup.chinchin.domain.model.FriendProfile
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

data class FriendProfileUiModel(
    val profile: ProfileUiModel,
    val myAnswers: List<QuestionUiModel>,
    val friendAnswers: List<QuestionUiModel>
) {
    companion object {
        fun fromDomainModel(friendProfile: FriendProfile): FriendProfileUiModel {
            return with(friendProfile) {
                FriendProfileUiModel(
                    profile = ProfileUiModel.fromDomainModel(profile),
                    myAnswers = myAnswers.map { QuestionUiModel.fromDomainModel(it) },
                    friendAnswers = friendAnswers.map { QuestionUiModel.fromDomainModel(it) }
                )
            }
        }

    }
}
