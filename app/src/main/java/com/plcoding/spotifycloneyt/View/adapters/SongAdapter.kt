package com.plcoding.spotifycloneyt.View.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.databinding.ListItemBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    val glide: RequestManager, val songs: List<Song>, private val listener: SongsCLicked?
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {

        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = SongViewHolder(view)
        return viewholder
    }


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]

        holder.itemView.apply {
            holder.name.text = song.title
            holder.subtitle.text = song.subTitle
            glide.load(song.imgUrl).into(holder.Img)



            holder.itemView.rootView.setOnClickListener {
                listener?.SongCLicked(song)
            }
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }


    class SongViewHolder(binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvPrimary
        var subtitle = binding.tvSecondary
        var Img = binding.ivItemImage
        var rootView = binding.root
    }

    interface SongsCLicked {

        fun SongCLicked(song: Song) {

        }
    }
}