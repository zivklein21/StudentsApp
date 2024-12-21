package com.example.studentsapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.databinding.ActivityEditStudentBinding
import com.example.studentsapp.models.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val studentId = intent.getStringExtra("studentId")
        student = StudentDatabase.students.find { it.id == studentId }

        if (student != null) {
            binding.etStudentName.setText(student!!.name)
            binding.etStudentId.setText(student!!.id)
            binding.etStudentPhone.setText(student!!.phone)
            binding.etStudentAddress.setText(student!!.address)

            binding.btnSaveStudent.setOnClickListener {
                val updatedName = binding.etStudentName.text.toString()
                val updatedId = binding.etStudentId.text.toString()
                val updatedPhone = binding.etStudentPhone.text.toString()
                val updatedAddress = binding.etStudentAddress.text.toString()

                if (updatedName.isEmpty() || updatedId.isEmpty() || updatedPhone.isEmpty() || updatedAddress.isEmpty()) {
                    Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Update student details in the database
                student?.apply {
                    name = updatedName
                    id = updatedId
                    phone = updatedPhone
                    address = updatedAddress
                }

                // Close the activity and send a result back
                setResult(RESULT_OK) // Indicate success
                finish()
            }


            binding.btnDeleteStudent.setOnClickListener {
                StudentDatabase.students.remove(student)
                finish()
            }

            binding.btnCancel.setOnClickListener {
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
