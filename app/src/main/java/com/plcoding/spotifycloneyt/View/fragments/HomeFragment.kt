package com.plcoding.spotifycloneyt.View.fragments

import android.os.Bundle
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.View.adapters.SongAdapter
import com.plcoding.spotifycloneyt.View.adapters.SwipeSongAdapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Genre_Adapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Language_Adapter
import com.plcoding.spotifycloneyt.Viewmodels.LangViewModelFactory
import com.plcoding.spotifycloneyt.Viewmodels.LanguageViewModel
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import com.plcoding.spotifycloneyt.Viewmodels.ViewModelFactory
import com.plcoding.spotifycloneyt.databinding.FragmentHomeBinding
import com.plcoding.spotifycloneyt.other.Status
import com.plcoding.spotifycloneyt.other.exoplayer.MusicServiceConnection
import com.plcoding.spotifycloneyt.other.exoplayer.isPlaying
import com.plcoding.spotifycloneyt.other.exoplayer.toSong
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), SongAdapter.SongsCLicked, SwipeSongAdapter.SwipeSongsCLicked {


    lateinit var mainViewModel: MainViewModel
    lateinit var languageViewModel: LanguageViewModel
    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var swipeSongAdapter: SwipeSongAdapter

    @Inject
    lateinit var musicServiceConnection: MusicServiceConnection

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var songAdapter: SongAdapter

    private var curPlayingSong: Song? = null
    private var playbackState: PlaybackStateCompat? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel =ViewModelProvider(requireActivity(), ViewModelFactory(musicServiceConnection)).get( MainViewModel::class.java )

        setupRecyclerView()
        RV_Language()
        RV_genre()

        subscribeToObservers()
        settingUpVP()

    }

    fun setupRecyclerView() {

        mainViewModel.mediaItems.observe(viewLifecycleOwner) { result ->

            when (result.status) {
                Status.SUCCESS -> {
                    binding.allSongsProgressBar.isVisible = false
                    result.data?.let { songs ->
                        val adapter = SongAdapter(glide, songs, this@HomeFragment)
                        binding.rvAllSongs.adapter = adapter
                        binding.rvAllSongs.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> {
                    binding.allSongsProgressBar.isVisible = true
                }
            }
        }
    }

    private fun RV_genre() {
        val GenreName = listOf("Love", "Metal", "Romantic", "90's-Hits", "OLD-is-GOLD")
        val imgList = listOf(
            R.drawable.love,
            R.drawable.metal,
            R.drawable.romance,
            R.drawable.nienteens_hits,
            R.drawable.goldenera,
        )
        val adapter = rv_Genre_Adapter(GenreName, imgList, requireContext())
        binding.rvLanguage.adapter = adapter
        binding.rvLanguage.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun RV_Language() {
        val LanguageName = listOf("English", "Panjabi", "Hindi", "Marathi", "Korean")


        val imgList = listOf(
            R.drawable.eng,
            R.drawable.panjabi,
            R.drawable.hindi,
            R.drawable.marathi,
            R.drawable.korian,
        )

        val adapter = rv_Language_Adapter(LanguageName, imgList, requireContext())
        binding.rvGenre.adapter = adapter
        binding.rvGenre.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }



    //-------------------------------MainActivity data---------------------------------

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(this) {
            it?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { songs ->

                            val swipeSongAdapter  =   SwipeSongAdapter(glide, songs, this)
                            binding.vpSong.adapter = swipeSongAdapter

                            // data is going correctly in SwipeSongAdapter
                            Log.d("mytag", "songs count in Mainactivity" + songs.size)
                            SwipeSongAdapter(glide, songs, this)


                            if (songs.isNotEmpty()) {
                                glide.load((curPlayingSong ?: songs[0]).imgUrl)
                                    .into(binding.ivCurSongImage)
                            }
                            switchViewPagerToCurrentSong(curPlayingSong ?: return@observe)
                        }
                    }
                    Status.ERROR -> Unit
                    Status.LOADING -> Unit
                }
            }
        }
        mainViewModel.curPlayingSong.observe(this) {
            if (it == null) return@observe

            curPlayingSong = it.toSong()
            glide.load(curPlayingSong?.imgUrl).into(binding.ivCurSongImage)
            switchViewPagerToCurrentSong(curPlayingSong ?: return@observe)
        }
        mainViewModel.playbackState.observe(this) {
            playbackState = it
            binding.ivPlayPause.setImageResource(
                if (playbackState?.isPlaying == true) R.drawable.ic_pause else R.drawable.ic_play
            )
        }
        mainViewModel.isConnected.observe(this) {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.ERROR -> Snackbar.make(
                        binding.root,
                        result.message ?: "An unknown error occured",
                        Snackbar.LENGTH_LONG
                    ).show()
                    else -> Unit
                }
            }
        }
        mainViewModel.networkError.observe(this) {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.ERROR -> Snackbar.make(
                        binding.root,
                        result.message ?: "An unknown error occured",
                        Snackbar.LENGTH_LONG
                    ).show()
                    else -> Unit
                }
            }
        }
    }

    private fun settingUpVP() {

        vpSong.adapter = swipeSongAdapter

        vpSong.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val songsList = swipeSongAdapter.songs
                if (songsList.isNotEmpty() && position >= 0 && position < songsList.size) {
                    if (playbackState?.isPlaying == true) {
                        mainViewModel.playOrToggleSong(songsList[position])
                    } else {
                        curPlayingSong = songsList[position]
                    }
                }
            }
        })

        binding.ivPlayPause.setOnClickListener {
            curPlayingSong?.let {
                mainViewModel.playOrToggleSong(it, true)
            }
        }

        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.songFragment -> hideBottomBar()
                R.id.homeFragment -> showBottomBar()
                else -> showBottomBar()
            }
        }
    }

    private fun hideBottomBar() {
        binding.ivCurSongImage.isVisible = false
        binding.vpSong.isVisible = false
        binding.ivPlayPause.isVisible = false
    }

    private fun showBottomBar() {
        binding.ivCurSongImage.isVisible = true
        binding.vpSong.isVisible = true
        binding.ivPlayPause.isVisible = true
    }

    private fun switchViewPagerToCurrentSong(song: Song) {
        val newItemIndex = swipeSongAdapter.songs.indexOf(song)
        if (newItemIndex != -1) {
            binding.vpSong.currentItem = newItemIndex
            curPlayingSong = song

        }
    }

    override fun SwipeSongCLicked(song: Song) {
        Toast.makeText( requireContext(),"swipeSongAdapter SwipeSong Click listener working \n Heading to SongFrag", Toast.LENGTH_SHORT ).show()
//        findNavController().navigate(R.id.globalActionToSongFragment)
        //todo : navigation towards SongFrag not working
    }

    override fun SongCLicked(song: Song) {
        mainViewModel.playOrToggleSong(song)

//        findNavController().navigate(R.id.globalActionToSongFragment)
        //todo : navigation towards SongFrag not working
    }
}