package dev.jeonghyeonji.imagesearch.model

/**
* Created by jeonghyeonji on 2017. 4. 6..
*/
data class Image(
        val title: String, //검색 제목
        val link: String, //서비스 URL
        val description: String, //검색 결과의 간략한 소개
        val lastBuildDate: String, // 검색 시간
        val totalCount: Int, //전체 검색 결과의 수(추정치)
        val pageCount: Int, //보여줄 수 있는 문서의 수(추정치)
        val result: Int, //한 페이지에 출력될 결과수
        val item: Array<ImageItem> //이미지 아이템

)