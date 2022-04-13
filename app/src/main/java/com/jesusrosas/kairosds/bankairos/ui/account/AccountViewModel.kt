package com.jesusrosas.kairosds.bankairos.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrosas.kairosds.bankairos.data.model.LocationProvider
import com.jesusrosas.kairosds.bankairos.data.model.UserProvider
import com.jesusrosas.kairosds.bankairos.domain.GetCardsUseCase
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private val getCardsUseCase = GetCardsUseCase()

    private val _title = MutableLiveData("Mis cuentas")
    val title: LiveData<String> get() = _title

    private val _frame = MutableLiveData("Accounts")
    val frame: LiveData<String> get() = _frame

    private val _name = MutableLiveData(UserProvider.getFullName())
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData(UserProvider.user.email)
    val email: LiveData<String> get() = _email

    private val _location = MutableLiveData(LocationProvider.location)
    val location: LiveData<String> get() = _location

    private val _isMsgVisible = MutableLiveData(false)
    val isMsgVisible: LiveData<Boolean> get() = _isMsgVisible

    private val _cardList = MutableLiveData<List<CardItem>>()
    val cardList: LiveData<List<CardItem>> get() = _cardList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun init() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getCardsUseCase()
            if (result.cards.isNotEmpty()) {
                for (element in result.cards) {
                    if (element.name == "Error") _isMsgVisible.value = true
                    else _cardList.value = result.cards

                }
            } else _isMsgVisible.value = true
            _isLoading.value = false
        }
    }
}