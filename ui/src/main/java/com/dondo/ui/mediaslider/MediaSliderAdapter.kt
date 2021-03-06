package com.dondo.ui.mediaslider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dondo.ui.databinding.ElementImageBinding
import com.dondo.ui.databinding.ElementVideoBinding
import com.google.android.exoplayer2.Player

class MediaSliderAdapter(private val isZoomEnable: Boolean) : RecyclerView
.Adapter<RecyclerView.ViewHolder>() {

    private val VIDEO_TYPE = 0
    private val IMAGE_TYPE = 1
    private val VIDEO_EXTENSION = ".mp4"

    private val elements = ArrayList<String>()
    private var player: Player? = null

    var onPictureTouchAction: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIDEO_TYPE -> VideoViewHolder(
                ElementVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ) { player = it }
            else -> ImageViewHolder(
                ElementImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                isZoomEnable,
                onPictureTouchAction
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(elements[position])
            is VideoViewHolder -> holder.bind(elements[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            elements[position].endsWith(VIDEO_EXTENSION) -> VIDEO_TYPE
            else -> IMAGE_TYPE
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setElements(elements: List<String>) {
        this.elements.clear()
        this.elements.addAll(elements)
        notifyDataSetChanged()
    }

    fun stopPlayer() {
        player?.stop()
    }
}
