package com.example.myfirstapp.models


import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.*
import java.io.File

class FileUpload {
    val supabase = createSupabaseClient("https://fauokmrzqpowzdiqqxxg.supabase.co/", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZhdW9rbXJ6cXBvd3pkaXFxeHhnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDE1ODcyNTYsImV4cCI6MjAxNzE2MzI1Nn0.3GYnldygSO7wCrKZVHkQyviW0LVwS6KdPpAqIVa-EcE") {
        install(Storage)
    }

    suspend fun uploadData(bytes: ByteArray) {
        supabase.storage["bucket_name"].upload("file_path", bytes)
    }

    //Or on JVM/Android: (This will stream the data from the file to supabase)
    //Function to upload file given an Object of file and path that is a String
    suspend fun uploadFile(file: File, path : String) {
        supabase.storage["images"].upload(path, file)
    }



}