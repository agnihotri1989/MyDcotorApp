package com.kshitiz.mydoctorapp.data.model

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class DoctorDto(
    val id: Long,
    val name: String,
    val specialty: String,
    val rating: Double,
    val available_time: String,
    val image_url: String?,
    val background_color: String,
    val created_at: String
) {
    fun toDomain(): com.kshitiz.mydoctorapp.model.Doctor = com.kshitiz.mydoctorapp.model.Doctor(
        id = id.toInt(),
        name = name,
        specialty = specialty,
        rating = rating,
        distance = available_time,
        imageRes = com.kshitiz.mydoctorapp.R.drawable.ic_medical_cross,
        color = Color(android.graphics.Color.parseColor(background_color))
    )
}