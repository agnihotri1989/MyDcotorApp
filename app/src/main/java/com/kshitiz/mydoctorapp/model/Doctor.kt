package com.kshitiz.mydoctorapp.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Doctor(
    val id: Int,
    val name: String,
    val specialty: String,
    val rating: Double,
    val distance: String,
    @DrawableRes val imageRes: Int,
    val color: Color
)

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes val iconRes: Int
)
