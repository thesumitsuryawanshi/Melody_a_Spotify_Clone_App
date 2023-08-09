package com.plcoding.spotifycloneyt.View.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.databinding.ListItemBinding
import javax.inject.Inject

class ExploreSongAdapter @Inject constructor(
    val glide: RequestManager,
    val songs: List<Song>,
    private val listener: ExploreAdapterSongsCLicked?
) : RecyclerView.Adapter<ExploreSongAdapter.ExploreSongViewHolder>() {

    lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreSongViewHolder {

        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = ExploreSongViewHolder(view)
        return viewholder
    }


    override fun onBindViewHolder(holder: ExploreSongViewHolder, position: Int) {
        val song = songs[position]

        val sTitle= song.title

        holder.itemView.apply {
            holder.name.text = sTitle
            holder.subtitle.text = song.subTitle
            glide.load(song.imgUrl).into(holder.Img)


            holder.itemView.rootView.setOnClickListener {
                listener?.ExploreAdapterSongCLicked(song)

                Snackbar.make(it,"Passing song to Song Frag", Snackbar.LENGTH_SHORT).show()

                val bundle = Bundle()
                bundle.putString("_Currentsong", Gson().toJson(song))
                Navigation.findNavController(it).navigate(R.id.globalActionToSongFragment,bundle)

            }
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    class ExploreSongViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvPrimary
        var subtitle = binding.tvSecondary
        var Img = binding.ivItemImage
        var rootView = binding.root
    }

    interface ExploreAdapterSongsCLicked {

        fun ExploreAdapterSongCLicked(song: Song) {

        }
    }
}