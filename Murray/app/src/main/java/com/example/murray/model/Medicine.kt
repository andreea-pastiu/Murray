package com.example.murray.model

import android.graphics.drawable.Drawable

data class Medicine(val name: String, val minutes: Int, val before: Boolean, val pills: Double, val details: String,
    val monday: Boolean, val tuesday: Boolean, val wednesday: Boolean, val thursday: Boolean, val friday: Boolean,
    val saturday: Boolean, val sunday: Boolean, val hour: Int, val minute: Int, val image: Drawable){
}
