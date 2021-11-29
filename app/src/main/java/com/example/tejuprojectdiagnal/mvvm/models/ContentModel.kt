package com.example.tejuprojectdiagnal.mvvm.models

data class ContentModel(
    var page: Page?
) {
    data class Page(
        var `content-items`: ContentItems?,
        var `page-num`: String?,
        var `page-size`: String?,
        var title: String?,
        var `total-Content-Items`: String?
    ) {
        data class ContentItems(
            var content: ArrayList<Content?>?
        ) {
            data class Content(
                var name: String?,
                var `poster-image`: String?
            )
        }
    }
}