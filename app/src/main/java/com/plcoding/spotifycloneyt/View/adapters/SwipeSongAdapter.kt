package com.plcoding.spotifycloneyt.View.adapters

import android.util.Log
import androidx.recyclerview.widget.AsyncListDiffer
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.databinding.SwipeItemBinding

//class OLDSwipeSongAdapter : BaseSongAdapter(R.layout.swipe_item) {
//
//
//
//    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
//        val song = songs[position]
//        holder.itemView.apply {
//            val text = "${song.title} - ${song.subTitle}"
//            tvPrimary.text = text
//
//            setOnClickListener {
//                onItemClickListener?.let { click ->
//                    click(song)
//                }
//            }
//        }
//    }
//
//
//
//}
//
//
//--------------------------------------------------------------------------------------------------------------------------------
//


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.Model.data.entities.Song
import javax.inject.Inject

class SwipeSongAdapter @Inject constructor(
    var glide: RequestManager, var songs: List<Song>, private val listener: SwipeSongsCLicked
) : RecyclerView.Adapter<SwipeSongAdapter.SwipeSongViewHolder>() {

    lateinit var binding: SwipeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeSongViewHolder {

        val view = SwipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = SwipeSongViewHolder(view)
        Toast.makeText(parent.context, "SwipeSongAdapter started", Toast.LENGTH_SHORT).show()

        return viewholder
    }


    override fun onBindViewHolder(holder: SwipeSongViewHolder, position: Int) {
        val song = songs[position]
        holder.itemView.apply {
            val text = "${song.title} - ${song.subTitle}"
            holder.name.text = text

            holder.itemView.rootView.setOnClickListener {
                listener.SwipeSongCLicked(song)
                Toast.makeText(holder.itemView.context, "swipe Song Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("mytag", "songs count in SwipesongAdapter" + songs.size)
        return songs.size
    }

    class SwipeSongViewHolder(binding: SwipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvPrimary
    }

    interface SwipeSongsCLicked {
        fun SwipeSongCLicked(song: Song) {

        }
    }
}


//
//interface SwipeSongsCLicked {
//    fun SwipeSongCLicked(song: Song) {
//
//    }
//
//    }