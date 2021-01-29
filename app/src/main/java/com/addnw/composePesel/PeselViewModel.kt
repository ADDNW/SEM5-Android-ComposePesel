package com.addnw.composePesel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeselViewModel : ViewModel() {
    companion object {
        const val LOG_KEY = "PeselVM"
        val weighs = listOf<Int>(1,3,7,9,1,3,7,9,1,3,1)
    }

    private val _pesel = MutableLiveData("")
    val pesel: LiveData<String> = _pesel

    var birthdateText = ""
    var genderText = ""
    var correctText = ""
    var valid = false

    fun onPeselChanged(newPesel: String) {
        _pesel.value = newPesel
        valid = newPesel.length == 11 && newPesel.toLongOrNull() != null
        if (valid) {
            birthdateText = "Born: ${newPesel.subSequence(4,6)}.${newPesel.subSequence(2,4)}.${newPesel.subSequence(0,2)}"
            genderText = if (Character.getNumericValue(newPesel[9]) % 2 == 0) {
                "Gender: Woman"
            } else {
                "Gender: Man"
            }
            correctText = if (validatePesel(newPesel)) {
                "Pesel is correct"
            } else {
                "Pesel is invalid"
            }
        }
    }

    private fun validatePesel(pesel: String): Boolean {
        var sum = 0
        for ((i, weight) in weighs.withIndex()) {
            sum += weight * Character.getNumericValue(pesel[i])
        }
        return sum % 10 == 0
    }
}
