package com.example.learnerapp.presentation.login
import androidx.lifecycle.ViewModel
import com.example.learnerapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _authState = MutableStateFlow(false)
    val authState: StateFlow<Boolean> = _authState

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun login(email: String, password: String) {

        repository.login(email, password) { success, message ->

            if (success) {
                _authState.value = true
            } else {
                _error.value = message ?: "Login failed"
            }
        }
    }

    fun register(email: String, password: String) {

        repository.register(email, password) { success, message ->

            if (success) {
                _authState.value = true
            } else {
                _error.value = message ?: "Registration failed"
            }
        }
    }
}