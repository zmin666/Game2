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

    lateinit var mPlayer: ExoPlayer
    val playUrl = "android.resource://" + packageName + "/" + R.raw.palying
    val playUrl2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
    val m1 by lazy { MediaItem.fromUri(Uri.parse(playUrl)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        mPlayer = ExoPlayer.Builder(this).build().apply {
            repeatMode = REPEAT_MODE_ONE
        }
        playerView.player = mPlayer
        mPlayer.setPlaybackSpeed(0.5f)
        mPlayer.setMediaItem(m1)
        mPlayer.prepare()
        ivCover.visibility = View.VISIBLE

        bt.setOnClickListener {
            if (mPlayer.isPlaying) {
                mPlayer.pause()
                mPlayer.seekTo(0)
                bt.text = "开"
                ivCover.visibility = View.VISIBLE
            } else {
                mPlayer.play()
                bt.text = "关"
                ivCover.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        mPlayer.release()
        super.onDestroy()
    }
}