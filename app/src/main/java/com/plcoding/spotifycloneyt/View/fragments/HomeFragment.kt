package com.plcoding.spotifycloneyt.View.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.View.adapters.SongAdapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Genre_Adapter
import com.plcoding.spotifycloneyt.View.adapters.rv_Language_Adapter
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import com.plcoding.spotifycloneyt.databinding.FragmentHomeBinding
import com.plcoding.spotifycloneyt.other.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), SongAdapter.SongCLicked {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var songAdapter: SongAdapter

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        subscribeToObservers()

        RV_Language()
        RV_genre()
    }


    private fun setupRecyclerView() = binding.rvAllSongs.apply {
        adapter = songAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun subscribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    binding.allSongsProgressBar.isVisible = false
                    result.data?.let { songs ->
                        SongAdapter(glide, songs, this)
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> binding.allSongsProgressBar.isVisible = true
            }
        }
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

    private fun RV_genre() {
        val GenreName = listOf("Love", "Metal", "Romantic", "90's Hits", "OLD is GOLD")


        val imgList = listOf(
            R.drawable.love,
            R.drawable.metal,
            R.drawable.romance,
            R.drawable.nienteens_hits,
            R.drawable.goldenera,
        )

        val adapter = rv_Genre_Adapter(GenreName, imgList, requireContext())
        binding.rvLanguage.adapter = adapter
        binding.rvLanguage.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun SongCLicked(song: Song) {
        mainViewModel.playOrToggleSong(song)
    }

}
