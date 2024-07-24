package com.example.avocado_android.domain.model.response.main

data class WeeklyAttendanceDto(
    val attendances: List<Boolean> = listOf(false, false, false, false, false, false, false)
)

