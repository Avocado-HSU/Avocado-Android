## AvocaDo
<p align="center">
 <img src="https://github.com/user-attachments/assets/5d413d5c-76dc-424b-ad4e-ed5c510bcefb" alt="아보카도"/>
</p>
<div align="center">
 생성형 AI 기반 영어단어 암기 보조 서비스 안드로이드 파트
</div>

## 목차
  - [개요](#개요)
  - [내용](#내용)
  - [화면](#화면)
  - [기술스택](#기술스택)
  - [저작권](#저작권)

## 개요
- 프로젝트 이름: AvocaDo (한성대학교 창업동아리)
- 프로젝트 지속기간: 2024.07.07~ 07/31
- 개발 엔진 및 언어: Android Studio, Kotlin
- 멤버: 박지원, 김성민

## 내용
- 로그인
    - 네이버 ,카카오로 소셜 로그인
- 홈 화면
    - 검색등의 활동을 하면 출석체크를 할 수 있고, 인기 검색어, 추천 단어를 눌러 단어 라이브러리로 가서 단어를 학습할 수 있다.
- 단어장
    - 단어를 검색하거나 단어장에 있는 단어, 홈 화면에 있는 단어를 눌러 단어를 학습할 수 있다
    - 단어를 라이브러리에 저장 및 삭제 할 수 있다
- 검색 화면
    - 단어를 검색하여 해당 단어를 단어장에서 공부할 수 있고, 최근 검색어 기능이 있다
- 라이브러리
    - 자신만의 라이브러리에 단어를 저장해 두고 볼 수 있다.
- 챗봇
    - 멀티 타입 리사이클러뷰 이용해서 구현
    - 파인 튜닝된 생성형 AI를 서버를 통해 4가지 선택지로 아래 기능을 사용할 수 있다.
        - 단어의 뜻
        - 유사한 단어
        - 단어 어원 분류 (어간, 접두/접미사, 어간 설명, 접두/접미사 설명, 어원설명)
        - 단어 외우기 팁

## 화면
<p align="center">
  <img src="https://github.com/user-attachments/assets/ede50a6a-d1c8-4ffd-964e-d66e87752215" alt="아보카도"/>
</p>

## 기술스택

### **🤖** 안드로이드
| **Category** | **TechStack** |
| --- | --- |
| Architecture | Clean Architecture, MVVM |
| DI | Hilt |
| Network | Retrofit, OkHttp, Gson |
| Asynchronous | Coroutines, Flow |
| Jetpack | DataBinding, Navigation, DataStore |
| Image | Glide |

## 저작권
Copyright 2024. 전세원 All rights reserved.
ⓒ 2024. 전세원 All rights reserved.
(c) 2024. 전세원 All rights reserved.
