package com.example.alphatesttasktimofeev.presentation.requestsList.state

import com.example.alphatesttasktimofeev.domain.entity.RequestRecord

sealed class RequestListState {
    data object Loading : RequestListState()
    data class AllRequests(val allRequests: List<RequestRecord>) : RequestListState()
    data class Error(val error: String) : RequestListState()
}