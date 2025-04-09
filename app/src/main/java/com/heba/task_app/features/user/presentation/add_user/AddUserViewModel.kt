package com.heba.task_app.features.user.presentation.add_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heba.task_app.features.user.data.local.entities.UserEntity
import com.heba.task_app.features.user.domain.use_case.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    private val _navigate= MutableSharedFlow<Boolean>()
    val navigate: SharedFlow<Boolean> = _navigate.asSharedFlow()

    fun addUser(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserUseCase.invoke(userEntity)
            _navigate.emit(true)
        }
    }
}