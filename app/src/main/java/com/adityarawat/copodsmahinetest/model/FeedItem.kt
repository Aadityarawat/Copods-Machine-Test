sealed class FeedItem(
    open val id: String,
    open val timestamp: Long,
    open val username: String
) {
    data class TextFeed(
        val textContent: String,
        val textStyle: TextStyle,
        override val id: String,
        override val timestamp: Long,
        override val username: String
    ) : FeedItem(id, timestamp, username)

    data class ImageFeed(
        val imageUrl: String,
        val description: String,
        override val id: String,
        override val timestamp: Long,
        override val username: String
    ): FeedItem(id, timestamp, username)

    data class VideoFeed(
        val videoUrl: String,
        val thumbnailUrl: String,
        override val id: String,
        override val timestamp: Long,
        override val username: String
    ): FeedItem(id, timestamp, username)
}

data class TextStyle(
    val fontSize: Int,
    val fontColor: String
)
