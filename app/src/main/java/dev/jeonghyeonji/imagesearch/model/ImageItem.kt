package dev.jeonghyeonji.imagesearch.model

/**
* Created by jeonghyeonji on 2017. 4. 6..
*/
data class ImageItem (
        val title    : String,    //개별 검색 결과의 제목
        val link    : String,    //개별 검색 결과의 link url
        val image    : String,    //이미지 URL
        val thumbnail    : String,    //썸네일 URL
        val width    : String,    //이미지의 가로 크기
        val height    : String,    //이미지의 세로 크기
        val pubDate    : String,    //등록일
        val cpname    : String    //컨텐츠 제공처
)