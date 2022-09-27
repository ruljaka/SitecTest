package com.ruslangrigoriev.sitectest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslangrigoriev.sitectest.domain.AuthenticateUseCase
import com.ruslangrigoriev.sitectest.domain.GetUserListUseCase
import com.ruslangrigoriev.sitectest.domain.entity.users.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val authenticateUseCase: AuthenticateUseCase,
) : ViewModel() {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _userNames: MutableLiveData<List<String>> = MutableLiveData()
    val userNames: LiveData<List<String>> = _userNames

    private var users: List<User>? = null

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _message.postValue(throwable.message)
        _loading.postValue(false)
    }

    fun fetchUserList() {
        viewModelScope.launch(exceptionHandler) {
            _loading.value = true
            users = getUserListUseCase()
            _userNames.value = users?.map { it.user }
            _loading.value = false
        }
    }

    fun login(userName: String, pass: String) {
        val uid = users?.find { it.user == userName }?.uid
        uid?.let {
            viewModelScope.launch(exceptionHandler) {
                _loading.value = true
                val authentication = authenticateUseCase(uid, pass)
                _message.postValue(
                    "Response: ${authentication?.response}" +
                            "\nContinueWork: ${authentication?.continueWork}" +
                            "\nPhotoHash: ${authentication?.photoHash}" +
                            "\nCurrentDate: ${authentication?.currentDate}"
                )
                _loading.value = false
            }
        }
    }

}