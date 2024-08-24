package com.adityarawat.copodsmahinetest.feed

import FeedItem
import android.view.View

interface FeedDisplay<T : FeedItem> {
    fun render(item: T): View
    fun handleInteraction(view: View, item: T)
}