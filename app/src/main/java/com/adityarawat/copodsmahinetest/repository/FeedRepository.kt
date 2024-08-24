package com.adityarawat.copodsmahinetest.repository

import FeedItem

interface FeedRepository {
    fun getFeedData(): List<FeedItem>
}