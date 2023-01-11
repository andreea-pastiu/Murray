package com.example.murray.model

data class Activity(var name: String, var details: String, var time: String, var status: String, var date: String, var recurrence: MutableList<Int>, var notificationID: Int): java.io.Serializable
