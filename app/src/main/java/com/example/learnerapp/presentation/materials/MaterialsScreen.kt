package com.example.learnerapp.presentation.materials
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnerapp.data.model.Material
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

@Composable
fun MaterialsScreen(
    navController: NavController,
    unit: String,
    viewModel: MaterialsViewModel
) {

    val context = LocalContext.current

    val categories = listOf(
        "Lecture Notes",
        "CATs",
        "Assignments",
        "Past Papers"
    )

    val db = FirebaseDatabase.getInstance().reference
    val storage = FirebaseStorage.getInstance().reference

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var materials by remember { mutableStateOf<List<Material>>(emptyList()) }

    // FILE PICKER
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        uri?.let {
            val input = context.contentResolver.openInputStream(it)
            val bytes = input?.readBytes()

            bytes?.let { fileBytes ->

                val id = db.push().key ?: return@let
                val ref = storage.child("materials/$unit/$selectedCategory/$id")

                ref.putBytes(fileBytes)
                    .addOnSuccessListener {
                        ref.downloadUrl.addOnSuccessListener { url ->

                            val material = Material(
                                id = id,
                                title = "File ${System.currentTimeMillis()}",
                                fileUrl = url.toString(),
                                levelId = unit
                            )

                            db.child("materials")
                                .child(unit)
                                .child(selectedCategory!!)
                                .child(id)
                                .setValue(material)

                            loadMaterials(unit, selectedCategory!!, db) {
                                materials = it
                            }
                        }
                    }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
                )
            )
            .padding(16.dp)
    ) {

        Text(
            text = "📘 $unit",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 CATEGORY LIST
        if (selectedCategory == null) {

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                items(categories.size) { index ->

                    val category = categories[index]

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCategory = category

                                loadMaterials(unit, category, db) {
                                    materials = it
                                }
                            },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.08f)
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = category,
                                fontSize = 18.sp,
                                color = Color.White
                            )

                            Text("➡️", color = Color.White)
                        }
                    }
                }
            }
        }

        // 🔹 MATERIALS UNDER CATEGORY
        else {

            Text(
                text = selectedCategory!!,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { launcher.launch("*/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Upload to $selectedCategory")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (materials.isEmpty()) {
                Text("No files yet", color = Color.White)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    items(materials.size) { index ->

                        val item = materials[index]

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(item.fileUrl)
                                    )
                                    context.startActivity(intent)
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(alpha = 0.08f)
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = item.title,
                                    color = Color.White
                                )

                                Text("⬇️", color = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "⬅ Back",
                color = Color.Gray,
                modifier = Modifier.clickable {
                    selectedCategory = null
                    materials = emptyList()
                }
            )
        }
    }
}
fun loadMaterials(
    unit: String,
    category: String,
    db: DatabaseReference,
    onResult: (List<Material>) -> Unit
) {
    db.child("materials")
        .child(unit)
        .child(category)
        .get()
        .addOnSuccessListener { snapshot ->

            val list = mutableListOf<Material>()

            for (child in snapshot.children) {
                val item = child.getValue(Material::class.java)
                item?.let { list.add(it) }
            }

            onResult(list)
        }
}