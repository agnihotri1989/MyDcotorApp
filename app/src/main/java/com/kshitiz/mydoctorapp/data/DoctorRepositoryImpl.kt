package com.kshitiz.mydoctorapp.data

import com.kshitiz.mydoctorapp.data.model.DoctorDto
import com.kshitiz.mydoctorapp.model.Doctor
import com.kshitiz.mydoctorapp.model.DoctorRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Count
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.PostgrestBuilder
import io.github.jan.supabase.postgrest.query.PostgrestFilterBuilder
import io.github.jan.supabase.postgrest.query.PostgrestResult
import kotlinx.serialization.json.Json
import java.lang.Exception

class DoctorRepositoryImpl : DoctorRepository {

    private val client: Postgrest = SupabaseClient.postgrest

    override suspend fun getAllDoctors(): Result<List<Doctor>> {
        return try {
            val builder = client.from("doctors")
            val response = builder.select(
                columns = "*",
                head = false,
                count = Count.EXACT,
                single = false
            ) {
                order("id", Order.ASCENDING, false, "")
            }
            val doctors = response.decodeAs<List<DoctorDto>>(Json.Default).map { it.toDomain() }
            Result.success(doctors)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDoctorById(id: Int): Result<Doctor?> {
        return try {
            val builder = client.from("doctors")
            val response = builder.select(
                columns = "*",
                head = false,
                count = Count.EXACT,
                single = false
            ) {
                eq("id", id.toString())
                limit(1)
            }
            val doctor = response.decodeAs<List<DoctorDto>>(Json.Default).firstOrNull()?.toDomain()
            Result.success(doctor)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}