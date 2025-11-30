package com.kshitiz.mydoctorapp.model

import androidx.compose.ui.graphics.Color
import com.kshitiz.mydoctorapp.R

object DoctorRepository {
    val doctors = listOf(
        Doctor(
            1, "Helena Mcneil", "General Practitioner", 4.9, "09:00 am - 02:00 pm",
            R.drawable.ic_medical_cross, Color(0xFFE8F1FF)
        ),
        Doctor(
            2, "Salman Pacheco", "Dentist", 4.8, "10:00 am - 04:00 pm",
            R.drawable.ic_medical_cross, Color(0xFFFFF5E5)
        ),
        Doctor(
            3, "Dr. John Doe", "Cardiologist", 4.7, "11:00 am - 03:00 pm",
            R.drawable.ic_medical_cross, Color(0xFFFFE8E8)
        )
    )

    fun getDoctorById(id: Int): Doctor? {
        return doctors.find { it.id == id }
    }
}
