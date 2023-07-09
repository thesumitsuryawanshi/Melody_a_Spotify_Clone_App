package com.plcoding.spotifycloneyt.View.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import com.plcoding.spotifycloneyt.databinding.ListItemBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    val glide: RequestManager, val songs: List<Song>, private val listener: SongCLicked?
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
            binding.tvPrimary.text = song.title
            binding.tvSecondary.text = song.subTitle
            glide.load(song.imgUrl).into(binding.ivItemImage)



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
        var Img = binding.tvSecondary
        var rootView = binding.root
    }

    interface SongCLicked {

        @SuppressLint("NotConstructor")
        fun SongCLicked(song: Song){

        }
    }

}
