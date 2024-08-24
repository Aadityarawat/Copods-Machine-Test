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
import com.bumptech.glide.Glide

class TextFeedRenderer(private val context: Context) : FeedDisplay<FeedItem.TextFeed> {
    override fun render(item: FeedItem.TextFeed): View {
        val textView = TextView(context)
        textView.text = item.textContent

        return textView
    }

    override fun handleInteraction(view: View, item: FeedItem.TextFeed) {
        view.setOnLongClickListener {
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
        val imageView = ImageView(context)
        loadImage(imageView, item.imageUrl)
        return imageView
    }

    override fun handleInteraction(view: View, item: FeedItem.ImageFeed) {
        view.setOnClickListener {
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
        val videoThumbnailView = ImageView(context)
        loadImage(videoThumbnailView, item.thumbnailUrl)
        return videoThumbnailView
    }

    override fun handleInteraction(view: View, item: FeedItem.VideoFeed) {
        view.setOnClickListener {
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
