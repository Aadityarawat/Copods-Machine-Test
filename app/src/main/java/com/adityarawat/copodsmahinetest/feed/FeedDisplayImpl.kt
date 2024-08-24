package com.adityarawat.copodsmahinetest.feed

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.ImageView
import com.adityarawat.copodsmahinetest.R

class TextFeedRenderer(private val context: Context) : FeedDisplay<FeedItem.TextFeed> {
    override fun render(item: FeedItem.TextFeed): View {
        // Create and return a TextView with the content of the text feed item
        val textView = TextView(context)
        textView.text = item.textContent
        // Additional styling based on TextStyle
        return textView
    }

    override fun handleInteraction(view: View, item: FeedItem.TextFeed) {
        view.setOnLongClickListener {
            // Copy text content to clipboard
            copyToClipboard(item.textContent)
            true
        }
    }
    private fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Text", text)
        clipboard.setPrimaryClip(clip)
    }
}

class ImageFeedRenderer(private val context: Context) : FeedDisplay<FeedItem.ImageFeed> {
    override fun render(item: FeedItem.ImageFeed): View {
        // Create and return an ImageView with the content of the image feed item
        val imageView = ImageView(context)
        loadImage(imageView, item.imageUrl) // Load image from URL
        return imageView
    }

    override fun handleInteraction(view: View, item: FeedItem.ImageFeed) {
        view.setOnClickListener {
            // Expand the image
            expandImage(item.imageUrl)
        }
    }
    private fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)
    }

    private fun expandImage(imageUrl: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_fullscreen_image)

        val imageView = dialog.findViewById<ImageView>(R.id.fullscreen_image_view)
        Glide.with(context)
            .load(imageUrl)
            .into(imageView)

        dialog.show()
    }
}

class VideoFeedRenderer(private val context: Context) : FeedDisplay<FeedItem.VideoFeed> {
    override fun render(item: FeedItem.VideoFeed): View {
        // Create and return a View with the thumbnail of the video feed item
        val videoThumbnailView = ImageView(context)
        loadImage(videoThumbnailView, item.thumbnailUrl) // Load thumbnail from URL
        return videoThumbnailView
    }

    override fun handleInteraction(view: View, item: FeedItem.VideoFeed) {
        view.setOnClickListener {
            // Play the video
            playVideo(item.videoUrl)
        }
    }
    private fun loadImage(imageView: ImageView, thumbnailUrl: String) {
        Glide.with(context)
            .load(thumbnailUrl)
            .into(imageView)
    }

    private fun playVideo(videoUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        intent.setDataAndType(Uri.parse(videoUrl), "video/*")
        context.startActivity(intent)
    }
}
