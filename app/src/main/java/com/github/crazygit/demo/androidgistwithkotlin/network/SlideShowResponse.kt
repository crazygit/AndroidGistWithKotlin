package com.github.crazygit.demo.androidgistwithkotlin.network


class SlideShowResponse(
    val slideshow: SlideShow
)

class SlideShow(
    val author: String,
    val date: String,
    val title: String,
    val slides: List<Slide>,
)

class Slide(
    val title: String,
    val type: String,
    val items: List<String>?
)