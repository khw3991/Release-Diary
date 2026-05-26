package com.example.mydiary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun InfoScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
        Text(text = "내 정보", fontSize = 20.sp)
    }
}
