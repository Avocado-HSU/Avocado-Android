package com.example.avocado_android.domain.model.response.main

data class MainPageResponseDto (
    val characterImageUrl : String = "",
    val message : String = "",
    val weeklyAttendanceDto: WeeklyAttendanceDto = WeeklyAttendanceDto(),
    val popularWordDto :  PopularWordDto = PopularWordDto(),
    val recommendWordDto : RecommendWordDto = RecommendWordDto()
)