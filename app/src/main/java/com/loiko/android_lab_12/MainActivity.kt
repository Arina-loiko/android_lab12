package com.loiko.android_lab_12

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loiko.android_lab_12.data.Datasource
import com.loiko.android_lab_12.model.Grade
import com.loiko.android_lab_12.ui.theme.GradeClickerTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        setContent {
            GradeClickerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GradeClickerApp(grades = Datasource().loadGrades())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }
}

@Composable
fun GradeClickerApp(grades: List<Grade>) {
    var points by rememberSaveable { mutableIntStateOf(0) }
    var clicks by rememberSaveable { mutableIntStateOf(0) }

    val currentGrade = getCurrentGrade(points, grades)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(currentGrade.gradeNameId),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(currentGrade.messageId),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Image(
            painter = painterResource(currentGrade.imageId),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .clickable {
                    points += currentGrade.pointsPerClick
                    clicks++
                }
        )

        TransactionInfo(points = points, clicks = clicks)
    }
}

fun getCurrentGrade(points: Int, grades: List<Grade>): Grade {
    var currentGrade = grades[0]
    for (grade in grades) {
        if (points >= grade.threshold) {
            currentGrade = grade
        }
    }
    return currentGrade
}

@Composable
fun TransactionInfo(points: Int, clicks: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.points_earned))
            Text(text = points.toString())
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.total_clicks))
            Text(text = clicks.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GradeClickerPreview() {
    GradeClickerTheme {
        GradeClickerApp(grades = Datasource().loadGrades())
    }
}
