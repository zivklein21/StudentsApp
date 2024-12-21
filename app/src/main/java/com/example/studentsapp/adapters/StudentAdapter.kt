package com.example.studentsapp.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.ItemStudentBinding
import com.example.studentsapp.models.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onCheckChange: (Student, Boolean) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.tvStudentName.text = student.name
            binding.tvStudentId.text = "ID: ${student.id}"
            binding.checkboxIsChecked.isChecked = student.isChecked

            binding.checkboxIsChecked.setOnCheckedChangeListener { _, isChecked ->
                onCheckChange(student, isChecked)
            }

            binding.root.setOnClickListener {
                onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size
}
