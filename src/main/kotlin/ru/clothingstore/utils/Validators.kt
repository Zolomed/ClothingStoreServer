package ru.clothingstore.utils

class Validators {
    companion object {
        fun isValidPhone(phoneNumber: String): Boolean {
            val cleanedPhoneNumber = phoneNumber.replace(Regex("[^\\d+]"), "")

            return cleanedPhoneNumber.length == 12
        }
    }
}