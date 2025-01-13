package com.example.alphatesttasktimofeev.presentation.cardInfo.state

import com.example.alphatesttasktimofeev.domain.entity.CardInfo

sealed class State {
    data object Initial : State()
    data object Loading : State()
    data object RequestNotCompleted : State()
    data class Success(val cardInfo: CardInfo) : State()
    data class Error(val error: String) : State()
}