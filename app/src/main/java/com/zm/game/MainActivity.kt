package com.zm.game

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * Author: zhang.min
 * Date: 2023/11/14 17:08
 * Description: MainActivity
 */
class MainActivity : AppCompatActivity() {

    private val playerView: PlayerView by lazy { findViewById<PlayerView>(R.id.player_view) }
    private val ivCover: ImageView by lazy { findViewById<ImageView>(R.id.ivCover) }
    private val bt: Button by lazy { findViewById<Button>(R.id.bt) }
    private val bt2: FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.bt2) }

    private lateinit var mPlayer: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        mPlayer = ExoPlayer.Builder(this).build().apply {
            repeatMode = REPEAT_MODE_ONE
        }
        playerView.player = mPlayer
        mPlayer.setPlaybackSpeed(0.5f)


        val playUrl0 = "android.resource://" + "/" + R.raw.p0
        mPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(playUrl0)))
        val playUrl1 = "android.resource://" + "/" + R.raw.p1
        mPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(playUrl1)))
        val playUrl2 = "android.resource://" + "/" + R.raw.p2
        mPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(playUrl2)))
        val playUrl3 = "android.resource://" + "/" + R.raw.p3
        mPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(playUrl3)))
        val playUrl4 = "android.resource://" + "/" + R.raw.p4
        mPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(playUrl4)))

        ivCover.visibility = View.VISIBLE
        mPlayer.prepare()

        bt.setOnClickListener {
            if (mPlayer.isPlaying) {
                mPlayer.pause()
                mPlayer.seekTo(0)
                bt.text = "开"
                when (mPlayer.currentMediaItemIndex) {
                    0 -> ivCover.setImageResource(R.mipmap.g0)
                    1 -> ivCover.setImageResource(R.mipmap.g1)
                    2 -> ivCover.setImageResource(R.mipmap.g2)
                    3 -> ivCover.setImageResource(R.mipmap.g3)
                    4 -> ivCover.setImageResource(R.mipmap.g4)
                }

                ivCover.visibility = View.VISIBLE
            } else {
                mPlayer.play()
                bt.text = "关"
                ivCover.visibility = View.GONE
            }
        }

        bt2.setOnClickListener {
            if (mPlayer.isPlaying) {
                mPlayer.pause()
                bt.text = "开"
            }
            if (mPlayer.hasNextMediaItem()) {
                mPlayer.seekToNextMediaItem()
            }else{
                mPlayer.seekToDefaultPosition(0)
            }
            when (mPlayer.currentMediaItemIndex) {
                0 -> ivCover.setImageResource(R.mipmap.g0)
                1 -> ivCover.setImageResource(R.mipmap.g1)
                2 -> ivCover.setImageResource(R.mipmap.g2)
                3 -> ivCover.setImageResource(R.mipmap.g3)
                4 -> ivCover.setImageResource(R.mipmap.g4)
            }
        }
    }

    override fun onDestroy() {
        mPlayer.release()
        super.onDestroy()
    }
}