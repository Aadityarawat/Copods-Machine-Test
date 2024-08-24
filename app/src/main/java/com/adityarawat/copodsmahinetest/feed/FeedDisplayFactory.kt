package com.adityarawat.copodsmahinetest.feed

import FeedItem
import android.content.Context

object FeedRendererFactory {
    fun getRenderer(item: FeedItem, context: Context): FeedDisplay<out FeedItem> {
        return when (item) {
            is FeedItem.TextFeed -> TextFeedRenderer(context)
            is FeedItem.ImageFeed -> ImageFeedRenderer(context)
            is FeedItem.VideoFeed -> VideoFeedRenderer(context)
            else -> throw IllegalArgumentException("Unknown FeedItem type: ${item::class.java}")
        }
    }
}

