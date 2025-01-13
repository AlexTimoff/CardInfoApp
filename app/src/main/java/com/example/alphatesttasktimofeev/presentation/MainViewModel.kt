package com.example.alphatesttasktimofeev.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alphatesttasktimofeev.domain.AllRequestsUseCase
import com.example.alphatesttasktimofeev.domain.GetCardInfoUseCase
import com.example.alphatesttasktimofeev.domain.entity.CardInfo
import com.example.alphatesttasktimofeev.domain.entity.RequestRecord
import com.example.alphatesttasktimofeev.presentation.cardInfo.state.State
import com.example.alphatesttasktimofeev.presentation.requestsList.state.RequestListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val allRequestsUseCase: AllRequestsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Initial)
    val state = _state.asStateFlow()

    private val _buttonState = Channel<Boolean>()
    val buttonState = _buttonState.receiveAsFlow()

    private val _stateList = MutableStateFlow<RequestListState>(RequestListState.Loading)
    val stateList = _stateList.asStateFlow()

    fun checkInput(value: Boolean) {
        viewModelScope.launch {
            _buttonState.send(value)
        }
    }

    fun onSearchButtonClick(inputText: String) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val cardInfo = getCardInfoUseCase.fetchCardInfo(inputText)
                if (cardInfo != null) {
                    _state.value = State.Success(cardInfo)
                    addRequestToDatabase(inputText, cardInfo)
                } else {
                    _state.value = State.RequestNotCompleted
                }
            } catch (e: Throwable) {
                _state.value = State.Error(e.message.toString())
            }
        }
    }

    fun showAllRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            _stateList.value = RequestListState.Loading
            try {
                val requests = allRequestsUseCase.getAllRequests()
                _stateList.value = RequestListState.AllRequests(requests)
            } catch (e: Throwable) {
                _stateList.value = RequestListState.Error(e.message.toString())
            }
        }
    }

    fun deleteAllRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            allRequestsUseCase.deleteRequests()
            showAllRequests()
        }
    }

    private suspend fun addRequestToDatabase(inputText: String, cardInfo: CardInfo) {
        val newRequest = RequestRecord(
            id = allRequestsUseCase.getAllRequests().size,
            bin = inputText,
            type = cardInfo.scheme.toString(),
            country = cardInfo.country?.name.toString(),
            bank = cardInfo.bank?.name.toString()
        )
        allRequestsUseCase.addRequest(newRequest)
    }
}