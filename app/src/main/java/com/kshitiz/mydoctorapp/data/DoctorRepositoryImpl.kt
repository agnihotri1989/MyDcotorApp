package com.kshitiz.mydoctorapp.data

import com.kshitiz.mydoctorapp.data.model.DoctorDto
import com.kshitiz.mydoctorapp.model.Doctor
import com.kshitiz.mydoctorapp.model.DoctorRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.serialization.json.Json
import java.lang.Exception

class DoctorRepositoryImpl : DoctorRepository {

    private val client: Postgrest = SupabaseClient.postgrest

    override suspend fun getAllDoctors(): Result<List<Doctor>> {
        return try {
            val response = client.from("doctors").select(
                columns = Columns.raw("*")
            ) {
                order("id", Order.ASCENDING)
            }
            val doctors = response.decodeAs<List<DoctorDto>>().map { it.toDomain() }
            Result.success(doctors)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDoctorById(id: Int): Result<Doctor?> {
        return try {
            val response = client.from("doctors").select(
                columns = Columns.raw("*")
            ) {
                filter {
                    eq("id", id)
                }
            }
            val doctor = response.decodeAs<List<DoctorDto>>().firstOrNull()?.toDomain()
            Result.success(doctor)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}