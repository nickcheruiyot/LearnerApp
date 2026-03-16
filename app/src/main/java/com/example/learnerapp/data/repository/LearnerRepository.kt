package com.example.learnerapp.data.repository
import com.example.learnerapp.data.model.Course
import com.example.learnerapp.data.model.Institution
import com.example.learnerapp.data.model.Material
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class LearnerRepository {

    private val db = FirebaseDatabase.getInstance().reference

    // Fetch all institutions
    fun getInstitutions() = callbackFlow<List<Institution>> {
        val ref = db.child("institutions")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Institution::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // Fetch courses for a specific institution
    fun getCourses(institutionId: String) = callbackFlow<List<Course>> {
        val ref = db.child("institutions").child(institutionId).child("courses")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Course::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // Fetch units for a specific course
    fun getUnits(institutionId: String, courseId: String) = callbackFlow<List<Unit>> {
        val ref = db.child("institutions")
            .child(institutionId)
            .child("courses")
            .child(courseId)
            .child("units")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Unit::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    // Fetch materials for a specific unit
    fun getMaterials(institutionId: String, courseId: String, unitId: String) = callbackFlow<List<Material>> {
        val ref = db.child("institutions")
            .child(institutionId)
            .child("courses")
            .child(courseId)
            .child("units")
            .child(unitId)
            .child("materials")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Material::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }
}