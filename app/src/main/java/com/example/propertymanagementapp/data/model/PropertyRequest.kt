package com.example.propertymanagementapp.data.model

data class PropertyRequest(
    var address: String="",
    var city: String="",
    var state: String="",
    var country: String="",
    var zipCode: String="",
    var isMultipleUnits:Boolean=false,
    var purchasePrice: String="",
    var isDashboard:Boolean=false,
    var image: String="",
    var isMortgage:Boolean=false,
//    var userId: String,
//    var userType: String
)