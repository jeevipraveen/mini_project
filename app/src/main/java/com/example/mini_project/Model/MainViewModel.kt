package com.example.mini_project.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel: ViewModel() {
    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean> = _isValid

    fun validateDetails(birthdate: String, panNumber: String) {
        val isBirthdateValid = isValidBirthdate(birthdate)
        val isPanValid = isValidPAN(panNumber)
        _isValid.value = isBirthdateValid && isPanValid
        Log.d("ghdghjca","$_isValid")
    }

    private fun isValidBirthdate(birthdate: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false
        return try {
            dateFormat.parse(birthdate)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun isValidPAN(panNumber: String): Boolean {
        return panNumber.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}".toRegex())
    }
}