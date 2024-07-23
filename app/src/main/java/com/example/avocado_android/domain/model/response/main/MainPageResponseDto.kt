package com.example.avocado_android.domain.model.response.main

data class MainPageResponseDto (
    val characterImageUrl : String,
    val message : String,
    val weeklyAttendanceDto : WeeklyAttendanceDto,
    val popularWordDto : PopularWordDto ,
    val recommendWordDto : RecommendWordDto
)