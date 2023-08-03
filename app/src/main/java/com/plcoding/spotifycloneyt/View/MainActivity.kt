package com.plcoding.spotifycloneyt.View

import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import com.plcoding.spotifycloneyt.Viewmodels.ViewModelFactory
import com.plcoding.spotifycloneyt.databinding.ActivityMainBinding
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

//    @Inject
//    lateinit var swipeSongAdapter: SwipeSongAdapter

    @Inject
    lateinit var glide: RequestManager

    private var curPlayingSong: Song? = null
    private var playbackState: PlaybackStateCompat? = null
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var musicServiceConnection: MusicServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        getSupportActionBar()?.hide()

        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(musicServiceConnection)
        ).get(MainViewModel::class.java)


    }


}