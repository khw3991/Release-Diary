package com.example.mydiary

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import com.google.android.material.bottomnavigation.BottomNavigationView

// 1. AppCompatActivity 대신 ComponentActivity를 상속받아야 합니다.
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    Column(){
        MessageCard(name = "Android")
        PreviewText()
        PreviewButton()
    }
}


@Composable
fun PreviewText() {
    Text("이것은 텍스트 미리보기")
}

@Composable
fun PreviewButton() {
    Button(onClick = {}) { Text("이것은 버튼 미리보기") }
}


