package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.local.home.MemberDto
import retrofit2.http.DELETE
import retrofit2.http.GET

interface MemberControlApi {

    // 회원 정보가 삭제 및 해당 회원과연관된 캐릭터가 삭제됩니다
    @DELETE("/api/super-user/delete-user")
    suspend fun deleteUser()   // ResponseDTO

    // 현재 로그인 중인 유저의 정보를 ResponseEntity<Member> 형태로 받아옴
    @GET("/api/user/me")
    suspend fun getCurrentUser() : MemberDto   // ResponseDTO

}