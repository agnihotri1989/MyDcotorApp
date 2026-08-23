package com.kshitiz.mydoctorapp.model

import com.kshitiz.mydoctorapp.model.Doctor

interface DoctorRepository {
    suspend fun getAllDoctors(): Result<List<Doctor>>
    suspend fun getDoctorById(id: Int): Result<Doctor?>
}