package com.heba.task_app.features.user.presentation.all_users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heba.task_app.core.common.Resource
import com.heba.task_app.features.user.domain.model.User
import com.heba.task_app.features.user.domain.use_case.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUserViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private val _error = MutableSharedFlow<String>()
    val error: SharedFlow<String> get() = _error

    private val _getAllUsers = MutableStateFlow<List<User>?>(null)
    val getAllUsers: StateFlow<List<User>?> get() = _getAllUsers


    fun getAllUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllUsersUseCase.invoke().collect {
                when (it) {
                    is Resource.Loading -> _loading.value = true
                    is Resource.Success -> {
                        _loading.value = false
                        _getAllUsers.value = it.model
                    }

                    is Resource.Failure -> {
                        _loading.value = false
                        _error.emit( "Unexpected error occurred")
                    }
                }
            }
        }
    }
}