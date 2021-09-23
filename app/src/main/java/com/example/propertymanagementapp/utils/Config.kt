package com.example.propertymanagementapp.utils

class Config {
    companion object{
        const val BASE_URL = "https://apolis-property-management.herokuapp.com/api/"

        const val END_POINT_REGISTER = "auth/register"
        const val END_POINT_LOGIN = "auth/login"
        const val END_POINT_TENANT = "tenant"
        const val END_POINT_PROPERTY = "property"

        const val TENANT = "tenant"
        const val LANDLORD = "landlord"

        const val ERROR_EMPTY_NAME_MESSAGE = "Please enter your name."
        const val ERROR_EMPTY_EMAIL_MESSAGE = "Please enter your email address."
        const val ERROR_EMPTY_PASSWORD_MESSAGE = "Please enter your password."
        const val ERROR_EMPTY_PROPERTY_ID = "Please enter your propertyId."
        const val ERROR_PASSWORD_LENGTH_MESSAGE = "Please enter password of minimum length 6."
        const val ERROR_PASSWORD_LENGTH_PROPERTYID = "Please enter property of minimum length 6."
        const val ERROR_MESSAGE = "Failed, please try again."


    }
}