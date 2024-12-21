package com.example.studentsapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.data.StudentDatabase
import com.example.studentsapp.databinding.ActivityStudentListBinding

class StudentListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        val adapter = StudentAdapter(
            StudentDatabase.students,
            onItemClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("studentId", student.id)
                startActivity(intent)
            },
            onCheckChange = { student, isChecked ->
                student.isChecked = isChecked
            }
        )
        binding.recyclerViewStudents.adapter = adapter

        binding.fabAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerViewStudents.adapter?.notifyDataSetChanged()
    }
}
