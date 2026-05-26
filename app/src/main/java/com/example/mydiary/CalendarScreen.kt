package com.example.mydiary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

// 💡 일반 텍스트 및 그리드 상수
private const val DAYS_IN_WEEK = 7
private const val MONTH_TEXT_SIZE = 22
private const val BUTTON_TEXT_SIZE = 20
private const val DAY_TEXT_SIZE = 14
private const val EMOJI_TEXT_SIZE = 18

// 💡 캘린더 계산용 꼬리표 상수
private const val FIRST_DAY_OF_MONTH = 1
private const val CALENDAR_MONTH_OFFSET = 1
private const val DAY_OF_WEEK_OFFSET = 1 // 👈 마지막 숨은 범인 검거!
private const val MONTH_DECREMENT = -1
private const val MONTH_INCREMENT = 1
private const val EVEN_CHECK_DIVISOR = 2

// 💡 비율 및 가중치 소수점 숫자 상수화
private const val WEIGHT_EQUAL = 1f
private const val ASPECT_RATIO_SQUARE = 1f
private const val ASPECT_RATIO_CELL = 0.8f

// 💡 dp 수치 관련 치트키 상수화
private const val CORNER_RADIUS = 8
private val SCREEN_PADDING_VAL = 16.dp
private val HEADER_HORIZONTAL_PADDING_VAL = 24.dp
private val EMPTY_SPACE_HEIGHT_VAL = 20.dp
private val ROW_VERTICAL_PADDING = 8.dp
private val WEEK_DAYS_SPACER_HEIGHT = 8.dp
private val GRID_SPACER_HEIGHT = 12.dp
private val CELL_PADDING = 4.dp
private const val LIGHT_GRAY_HEX = 0xFFF5F5F5L
private val CARD_BACKGROUND_COLOR = Color(LIGHT_GRAY_HEX)

@Composable
fun CalendarScreen() {
    var calendarState by remember { mutableStateOf(Calendar.getInstance()) }

    val year = calendarState.get(Calendar.YEAR)
    val month = calendarState.get(Calendar.MONTH) + CALENDAR_MONTH_OFFSET

    val clonedCalendar = calendarState.clone() as Calendar
    clonedCalendar.set(Calendar.DAY_OF_MONTH, FIRST_DAY_OF_MONTH)
    val daysInMonth = clonedCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    // 💡 보정값 1을 상수로 교체 완료!
    val firstDayOfWeek = (clonedCalendar.get(Calendar.DAY_OF_WEEK) - DAY_OF_WEEK_OFFSET) % DAYS_IN_WEEK

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SCREEN_PADDING_VAL),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(
            year = year,
            month = month,
            onPreviousMonth = {
                val cal = calendarState.clone() as Calendar
                cal.add(Calendar.MONTH, MONTH_DECREMENT)
                calendarState = cal
            },
            onNextMonth = {
                val cal = calendarState.clone() as Calendar
                cal.add(Calendar.MONTH, MONTH_INCREMENT)
                calendarState = cal
            }
        )

        Spacer(modifier = Modifier.height(WEEK_DAYS_SPACER_HEIGHT))
        CalendarWeekDays()
        Spacer(modifier = Modifier.height(GRID_SPACER_HEIGHT))

        CalendarGrid(
            daysInMonth = daysInMonth,
            firstDayOfWeek = firstDayOfWeek,
            currentMonthValue = month
        )
    }
}

@Composable
private fun CalendarHeader(
    year: Int,
    month: Int,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = ROW_VERTICAL_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onPreviousMonth) {
            Text(text = "<", fontSize = BUTTON_TEXT_SIZE.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = "${year}년 ${month}월",
            fontSize = MONTH_TEXT_SIZE.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = HEADER_HORIZONTAL_PADDING_VAL)
        )

        IconButton(onClick = onNextMonth) {
            Text(text = ">", fontSize = BUTTON_TEXT_SIZE.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun CalendarWeekDays() {
    val daysOfWeek = listOf("일", "월", "화", "수", "목", "금", "토")
    Row(modifier = Modifier.fillMaxWidth()) {
        daysOfWeek.forEach { day ->
            val textColor = when (day) {
                "일" -> Color.Red
                "토" -> Color.Blue
                else -> Color.Gray
            }
            Text(
                text = day,
                modifier = Modifier.weight(WEIGHT_EQUAL),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

@Composable
private fun CalendarGrid(daysInMonth: Int, firstDayOfWeek: Int, currentMonthValue: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(DAYS_IN_WEEK),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(firstDayOfWeek) {
            Spacer(modifier = Modifier.aspectRatio(ASPECT_RATIO_SQUARE))
        }

        items(daysInMonth) { index ->
            val day = index + 1
            Column(
                modifier = Modifier
                    .padding(CELL_PADDING)
                    .aspectRatio(ASPECT_RATIO_CELL)
                    .background(
                        CARD_BACKGROUND_COLOR,
                        shape = RoundedCornerShape(CORNER_RADIUS.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = day.toString(),
                    fontSize = DAY_TEXT_SIZE.sp,
                    modifier = Modifier.padding(top = CELL_PADDING)
                )

                if ((day + currentMonthValue) % EVEN_CHECK_DIVISOR == 0) {
                    Text(
                        text = "💛",
                        fontSize = EMOJI_TEXT_SIZE.sp,
                        modifier = Modifier.padding(bottom = CELL_PADDING)
                    )
                } else {
                    Spacer(modifier = Modifier.height(EMPTY_SPACE_HEIGHT_VAL))
                }
            }
        }
    }
}
