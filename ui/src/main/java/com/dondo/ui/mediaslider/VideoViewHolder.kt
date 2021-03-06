package com.dondo.ui.mediaslider

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.dondo.ui.databinding.ElementVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player

class VideoViewHolder(
    private val binding: ElementVideoBinding,
    private val getPlayer: (Player) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(url: String) {
        binding.run {
            val player = ExoPlayer.Builder(binding.root.context).build()
            val mediaItem = MediaItem.fromUri(Uri.parse(url))
            playerView.player = player
            getPlayer(player)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady
        }
    }
}
