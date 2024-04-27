package com.example.mini_project.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel: ViewModel() {
    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean> = _isValid

    fun validateDetails(birthdateday: String,birthdatemonth: String,birthdateyear: String, panNumber: String) {

        val isBirthdatedayValidETC1 = birthdateday
        val isBirthdatemonthValid1 = birthdatemonth
        val isBirthdateyearValid1 = birthdateyear
        val birthdate = isBirthdatedayValidETC1 +isBirthdatemonthValid1+isBirthdateyearValid1

        val isBirthdateValid = isValidBirthdate(birthdate)
        val isPanValid = isValidPAN(panNumber)
        _isValid.value = isBirthdateValid && isPanValid
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