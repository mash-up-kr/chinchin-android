package com.mashup.chinchin.presenter.main.recommend_friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.GetRecommendedFriendsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendFriendsViewModel @Inject constructor(
    private val getRecommendedFriendsUseCase: GetRecommendedFriendsUseCase
): ViewModel() {

    init {
        getRecommendedFriends()
    }

    private fun getRecommendedFriends() {
        viewModelScope.launch {
            getRecommendedFriendsUseCase("kakaoToken")
        }
    }
}