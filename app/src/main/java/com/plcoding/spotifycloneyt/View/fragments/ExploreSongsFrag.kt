package com.plcoding.spotifycloneyt.View.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.View.adapters.ExploreSongAdapter
import com.plcoding.spotifycloneyt.Viewmodels.LangViewModelFactory
import com.plcoding.spotifycloneyt.Viewmodels.LanguageViewModel
import com.plcoding.spotifycloneyt.Viewmodels.MainViewModel
import com.plcoding.spotifycloneyt.databinding.FragmentExploreSongsBinding
import javax.inject.Inject

class ExploreSongsFrag : Fragment(), ExploreSongAdapter.ExploreAdapterSongsCLicked {

    lateinit var binding: FragmentExploreSongsBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var languageViewModel: LanguageViewModel

    @Inject
    lateinit var glide: RequestManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentExploreSongsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        languageViewModel = ViewModelProvider(
            requireActivity(),
            LangViewModelFactory()
        ).get(LanguageViewModel::class.java)

        // Initialize glide instance using injected dependency
        glide = Glide.with(this)
        assignDataToUi()
    }

    private fun assignDataToUi() {

        val _GenreL = arguments?.getString("Love")
        val _Love = Gson().fromJson(_GenreL, String::class.java)

        val _GenreM = arguments?.getString("Metal")
        val _Metal = Gson().fromJson(_GenreM, String::class.java)

        val _GenreR = arguments?.getString("Romantic")
        val _Romantic = Gson().fromJson(_GenreR, String::class.java)

        val _Genre90 = arguments?.getString("90's-Hits")
        val _90sHits = Gson().fromJson(_Genre90, String::class.java)

        val _GenreOiD = arguments?.getString("OLD-is-GOLD")
        val _OiD = Gson().fromJson(_GenreOiD, String::class.java)

        when (_Love) {
            "Love" ->
                mainViewModel.getGenreLoveData.observe(viewLifecycleOwner) { songs ->
                    val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                    binding.rvExploreAllSongs.adapter = adapter
                    binding.rvExploreAllSongs.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
        }
        when (_Metal) {
            "Metal" -> mainViewModel.getGenreMetalData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }

        }
        when (_Romantic) {
            "Romantic" ->
                mainViewModel.getGenreRomanticData.observe(viewLifecycleOwner) { songs ->
                    val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                    binding.rvExploreAllSongs.adapter = adapter
                    binding.rvExploreAllSongs.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
        }
        when (_90sHits) {
            "90's-Hits" -> mainViewModel.getGenre90hitsData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }
        when (_OiD) {
            "OLD-is-GOLD" -> mainViewModel.getGenreOldIsGoldData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }

//--------------------------------------------------------------------------------------------------------------------------------
        val _LangEnglish = arguments?.getString("English")
        val _English = Gson().fromJson(_LangEnglish, String::class.java)


        val _LangPanjabi = arguments?.getString("Panjabi")
        val _Panjabi = Gson().fromJson(_LangPanjabi, String::class.java)


        val _LangHindi = arguments?.getString("Hindi")
        val _Hindi = Gson().fromJson(_LangHindi, String::class.java)

        val _LangMarathi = arguments?.getString("Marathi")
        val _Marathi = Gson().fromJson(_LangMarathi, String::class.java)

        val _LangKorean = arguments?.getString("Korean")
        val _Korean = Gson().fromJson(_LangKorean, String::class.java)

        when (_English) {
            "English" -> languageViewModel.getLanguageEnglishData.observe(viewLifecycleOwner) { songs ->

                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }
        when (_Panjabi) {
            "Panjabi" -> languageViewModel.getLanguagePanjabiData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }

        }
        when (_Hindi) {
            "Hindi" ->
                languageViewModel.getLanguageHindiData.observe(viewLifecycleOwner) { songs ->
                    val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                    binding.rvExploreAllSongs.adapter = adapter
                    binding.rvExploreAllSongs.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
        }
        when (_Marathi) {
            "Marathi" -> languageViewModel.getLanguageMarathiData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }
        when (_Korean) {
            "Korean" -> languageViewModel.getLanguageKoreanData.observe(viewLifecycleOwner) { songs ->
                val adapter = ExploreSongAdapter(glide, songs, this@ExploreSongsFrag)
                binding.rvExploreAllSongs.adapter = adapter
                binding.rvExploreAllSongs.layoutManager =
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        }
    }

    override fun ExploreAdapterSongCLicked(song: Song) {
        Toast.makeText(requireContext(), "Song Click SUCC", Toast.LENGTH_SHORT).show()
//        Navigation.findNavController(requireActivity()).navigate()
    }
}