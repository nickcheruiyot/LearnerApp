package com.example.learnerapp.data
import com.google.firebase.database.FirebaseDatabase

object RealtimeDb {
    val instance: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance()
    }
}