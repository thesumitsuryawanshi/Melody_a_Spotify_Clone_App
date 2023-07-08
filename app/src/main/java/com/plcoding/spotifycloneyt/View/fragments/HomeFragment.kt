package com.plcoding.spotifycloneyt.View.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.View.adapters.SongAdapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Genre_Adapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Language_Adapter
import com.plcoding.spotifycloneyt.other.Status
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var  glide: RequestManager

    @Inject
    lateinit var songAdapter: SongAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        subscribeToObservers()

        songAdapter.setItemClickListener {
            mainViewModel.playOrToggleSong(it)
        }



        RV_Language()
        RV_genre()
    }

    private fun setupRecyclerView() = rvAllSongs.apply {
        adapter = songAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner) { result ->
            when(result.status) {
                Status.SUCCESS -> {
                    allSongsProgressBar.isVisible = false
                    result.data?.let { songs ->
                        songAdapter.songs = songs
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> allSongsProgressBar.isVisible = true
            }
        }
    }

    private fun RV_Language() {
        val LanguageName =
            listOf("English", "Hindi", "Marathi", "Tamil", "Telugu")
        val imgList = listOf(
            R.drawable.ic_play,
            R.drawable.ic_pause,
            R.drawable.ic_home,
            R.drawable.ic_play,
            R.drawable.ic_pause,
        )

        val adapter =rv_Language_Adapter(LanguageName, imgList, requireContext() )
        rv_genre.adapter = adapter
        rv_genre.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun RV_genre() {
        val GenreName =
            listOf("Love","Electronics","Romantic", "90's Heats", "The Golden Era")
        val imgList = listOf(
            R.drawable.ic_play,
            R.drawable.ic_pause,
            R.drawable.ic_home,
            R.drawable.ic_play,
            R.drawable.ic_pause,
        )

        val adapter =  rv_Genre_Adapter(GenreName, imgList, requireContext())
        rv_language.adapter = adapter
        rv_language.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}
















