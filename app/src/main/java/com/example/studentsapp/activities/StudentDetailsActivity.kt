package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentId = intent.getStringExtra("studentId")
        updateStudentDetails()

        binding.btnEditStudent.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentId", studentId)
            startActivityForResult(intent, REQUEST_EDIT_STUDENT)
        }

        // Navigate to the Student List Activity when Home button is clicked
        binding.btnHome.setOnClickListener {
            val intent = Intent(this, StudentListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun updateStudentDetails() {
        val student = StudentDatabase.students.find { it.id == studentId }
        if (student != null) {
            binding.tvStudentName.text = "Name: ${student.name}"
            binding.tvStudentId.text = "ID: ${student.id}"
            binding.tvStudentPhone.text = "Phone: ${student.phone}"
            binding.tvStudentAddress.text = "Address: ${student.address}"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_EDIT_STUDENT && resultCode == RESULT_OK) {
            updateStudentDetails()
        }
    }

    companion object {
        private const val REQUEST_EDIT_STUDENT = 1
    }
}
