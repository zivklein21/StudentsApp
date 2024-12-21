package com.example.studentsapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.databinding.ActivityNewStudentBinding
import com.example.studentsapp.models.Student

class NewStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSaveStudent.setOnClickListener {
            val name = binding.etStudentName.text.toString()
            val id = binding.etStudentId.text.toString()
            val phone = binding.etStudentPhone.text.toString()
            val address = binding.etStudentAddress.text.toString()

            if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            StudentDatabase.students.add(Student(id, name, phone, address))
            Toast.makeText(this, "Student added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
