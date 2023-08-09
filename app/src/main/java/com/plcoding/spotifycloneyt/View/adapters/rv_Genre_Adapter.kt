package com.plcoding.spotifycloneyt.View.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.databinding.HRvCategoriesBinding
import javax.inject.Inject
import com.google.gson.Gson

class rv_Genre_Adapter
@Inject
constructor(
    val Genre: List<String>,
    val ImgList: List<Int>,
    val context: Context
) : RecyclerView.Adapter<rv_Genre_Adapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {

        val view = HRvCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = GenreViewHolder(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val _Gname = Genre[position]
        val _currentImg = ImgList[position]

        holder.name.text = _Gname

        Glide.with(context)
            .load(_currentImg)
            .into(holder.Img)



        holder.itemView.setOnClickListener {View->

            val bundle = Bundle()
            bundle.putString("$_Gname", Gson().toJson(_Gname))
            Navigation.findNavController(View).navigate(R.id.globalActionToexploreSongsFrag,bundle)
        }
    }

    override fun getItemCount(): Int {
        return Genre.size
    }

    class GenreViewHolder(binding: HRvCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvCategory
        var Img = binding.ivCategories
        var rootView = binding.root
    }
}