package com.kshitiz.mydoctorapp.data

import android.content.Context
import com.kshitiz.mydoctorapp.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    private var instance: SupabaseClient? = null

    fun initialize(context: Context) {
        if (instance == null) {
            val url = BuildConfig.SUPABASE_URL
            val key = BuildConfig.SUPABASE_ANON_KEY
            instance = createSupabaseClient(url, key) {
                install(Postgrest)
                install(Realtime)
            }
        }
    }

    val supabase: SupabaseClient
        get() = instance ?: throw IllegalStateException("SupabaseClient not initialized. Call initialize(context) first.")
    
    val postgrest: Postgrest
        get() = supabase.postgrest
}