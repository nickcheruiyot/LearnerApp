package com.example.learnerapp.presentation.materials
import androidx.lifecycle.ViewModel
import com.example.learnerapp.data.model.Material
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class MaterialsState {
    object Loading : MaterialsState()
    data class Success(val materials: List<Material>) : MaterialsState()
    object Empty : MaterialsState()
}

class MaterialsViewModel : ViewModel() {

    private val _state = MutableStateFlow<MaterialsState>(MaterialsState.Loading)
    val state: StateFlow<MaterialsState> = _state.asStateFlow()

    private val db = FirebaseDatabase.getInstance().reference
    private val storage = FirebaseStorage.getInstance().reference

    //  LOAD FROM FIREBASE
    fun loadMaterials(unit: String) {

        _state.value = MaterialsState.Loading

        db.child("materials").child(unit)
            .get()
            .addOnSuccessListener { snapshot ->

                val list = mutableListOf<Material>()

                for (child in snapshot.children) {
                    val material = child.getValue(Material::class.java)
                    material?.let { list.add(it) }
                }

                _state.value = if (list.isEmpty()) {
                    MaterialsState.Empty
                } else {
                    MaterialsState.Success(list)
                }
            }
    }

    //  UPLOAD FILE
    fun uploadMaterial(
        unit: String,
        title: String,
        fileBytes: ByteArray
    ) {

        val id = db.push().key ?: return

        val storageRef = storage.child("materials/$unit/$id")

        storageRef.putBytes(fileBytes)
            .addOnSuccessListener {

                storageRef.downloadUrl.addOnSuccessListener { uri ->

                    val material = Material(
                        id = id,
                        title = title,
                        fileUrl = uri.toString(),
                        levelId = unit
                    )

                    db.child("materials")
                        .child(unit)
                        .child(id)
                        .setValue(material)

                    //  refresh list after upload
                    loadMaterials(unit)
                }
            }
    }
}