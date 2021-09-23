package com.example.propertymanagementapp.utils

class Validations {
    companion object{

        fun isValidPassword(password: String):Boolean{
            val regex = Regex("[a-zA-Z0-9]{6,}")
            return regex.matches(password)
        }

        fun isValidPropertyId(id: String):Boolean{
            val regex = Regex("[a-zA-Z0-9]{5,}")
            return regex.matches(id)
        }
    }

}