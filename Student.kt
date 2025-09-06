fun main() {
    val students = mutableListOf<Student>() // store multiple students

    while (true) {
        println("Student Management System")
        println("1. Add Student")
        println("2. Show All Students")
        println("3. Update Student Marks")
        println("4. Delete Student")
        println("5. Show Students Sorted by Average")
        println("6. Exit")
        print("Choose an option: ")

        when (readLine()!!.toInt()) {
            1 -> {
                print("Enter student name: ")
                val name = readLine()!!

                print("Enter marks (comma separated): ")
                val marksInput = readLine()!!
                val marks = marksInput.split(",").map { it.trim().toInt() }.toIntArray()

                val student = Student(name, marks)
                students.add(student)
                println("✅ Student added successfully!")
            }
            2 -> {
                if (students.isEmpty()) println("No students added yet.")
                else students.forEach { it.displayInfo(); println() }
            }
            3 -> {
                print("Enter student name to update marks: ")
                val name = readLine()!!
                val student = students.find { it.name.equals(name, true) }
                if (student != null) {
                    print("Enter new marks (comma separated): ")
                    val marksInput = readLine()!!
                    student.marks = marksInput.split(",").map { it.trim().toInt() }.toIntArray()
                    println("✅ Marks updated successfully!")
                } else println("❌ Student not found.")
            }
            4 -> {
                print("Enter student name to delete: ")
                val name = readLine()!!
                if (students.removeIf { it.name.equals(name, true) }) {
                    println("✅ Student deleted successfully!")
                } else println("❌ Student not found.")
            }
            5 -> {
                if (students.isEmpty()) println("No students added yet.")
                else {
                    val sorted = students.sortedByDescending { it.calculateAverage() }
                    println("\n📊 Students Sorted by Average:")
                    sorted.forEach { it.displayInfo(); println() }
                }
            }
            6 -> {
                println("Exiting... 👋")
                break
            }
            else -> println("Invalid option, try again.")
        }
    }
}

// Student class
class Student(val name: String, var marks: IntArray) {  // marks now mutable

    fun calculateAverage(): Double = marks.average()

    fun calculateGrade(): String {
        val avg = calculateAverage()
        return when {
            avg >= 75 -> "A"
            avg >= 60 -> "B"
            avg >= 50 -> "C"
            else -> "F"
        }
    }

    fun displayInfo() {
        println("Student: $name")
        println("Marks: ${marks.joinToString(", ")}")
        println("Average: ${"%.2f".format(calculateAverage())}")
        println("Grade: ${calculateGrade()}")
    }
}