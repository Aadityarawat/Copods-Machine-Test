package com.adityarawat.copodsmahinetest.feed

import android.content.Context
import android.view.View
import com.adityarawat.copodsmahinetest.repository.FeedRepository

class FeedManager(private val feedRepository: FeedRepository) {

    fun renderFeed(context: Context): List<View> {
        val feedItems = feedRepository.getFeedData()
        val views = mutableListOf<View>()

        for (item in feedItems) {
            val renderer = FeedRendererFactory.getRenderer(item, context)

            // Use smart casts to handle the correct item type
            val view = when (item) {
                is FeedItem.TextFeed -> {
                    val specificRenderer = renderer as FeedDisplay<FeedItem.TextFeed>
                    val view = specificRenderer.render(item)
                    specificRenderer.handleInteraction(view, item)
                    view
                }
                is FeedItem.ImageFeed -> {
                    val specificRenderer = renderer as FeedDisplay<FeedItem.ImageFeed>
                    val view = specificRenderer.render(item)
                    specificRenderer.handleInteraction(view, item)
                    view
                }
                is FeedItem.VideoFeed -> {
                    val specificRenderer = renderer as FeedDisplay<FeedItem.VideoFeed>
                    val view = specificRenderer.render(item)
                    specificRenderer.handleInteraction(view, item)
                    view
                }
                else -> throw IllegalArgumentException("Unknown FeedItem type: ${item::class.java}")
            }

            views.add(view)
        }

        return views
    }
}

