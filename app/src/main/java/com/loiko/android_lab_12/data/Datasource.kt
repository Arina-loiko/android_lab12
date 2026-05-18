package com.loiko.android_lab_12.data

import com.loiko.android_lab_12.R
import com.loiko.android_lab_12.model.Grade

class Datasource {
    fun loadGrades(): List<Grade> {
        return listOf(
            Grade(R.drawable.grade_0, 5, 0, R.string.grade_fail, R.string.message_fail),
            Grade(R.drawable.grade_50, 5, 50, R.string.grade_pass, R.string.message_pass),
            Grade(R.drawable.grade_70, 5, 70, R.string.grade_good, R.string.message_good),
            Grade(R.drawable.grade_90, 5, 90, R.string.grade_excellent, R.string.message_excellent),
            Grade(R.drawable.grade_100, 5, 100, R.string.grade_excellent, R.string.message_excellent)
        )
    }
}
