package com.example.streamappkotlin

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var progressBar: View
    private lateinit var uri: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_activity)

        val bundle: Bundle? = intent.extras
        uri = bundle!!.getString("fileUri").toString()
        playerView = findViewById(R.id.videoPlayer)
        progressBar = findViewById(R.id.videoProgressBar)

    }

    private fun initializePlayer() {
        progressBar.visibility = View.GONE
        val videoUri = Uri.parse(uri)
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory =
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val loadControl = DefaultLoadControl()
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl)
        val defaultBandwidthMeter = DefaultBandwidthMeter()
        val dataSourceFactory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, "ExoPlayer"),
            defaultBandwidthMeter
        )

        val hlsMediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
        playerView.player = simpleExoPlayer
        simpleExoPlayer.prepare(hlsMediaSource)
        simpleExoPlayer.playWhenReady = true
        playerView.keepScreenOn = true
        simpleExoPlayer.addListener(object : Player.EventListener {

            override fun onLoadingChanged(isLoading: Boolean) {
                if (isLoading) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.VISIBLE

                }
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_BUFFERING) {

                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.VISIBLE

                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        if (simpleExoPlayer != null) {
            simpleExoPlayer.playWhenReady = false
        }
    }

    override fun onRestart() {
        super.onRestart()
        simpleExoPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }


}