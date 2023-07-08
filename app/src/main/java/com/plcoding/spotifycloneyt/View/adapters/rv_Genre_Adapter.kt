package com.plcoding.spotifycloneyt.View.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plcoding.spotifycloneyt.databinding.HRvCategoriesBinding
import javax.inject.Inject

class rv_Genre_Adapter
@Inject
constructor
    (
    val category: List<String>,
    val ImgList: List<Int>,
    val context: Context
) : RecyclerView.Adapter<rv_Genre_Adapter.GenreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {

        val view = HRvCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = GenreViewHolder(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val _Cname = category[position]
        val _currentImg = ImgList[position]

        holder.name.text = _Cname

        Glide.with(context)
            .load(_currentImg)
            .into(holder.Img)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "App working", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return category.size
    }

    class GenreViewHolder(binding: HRvCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvCategory
        var Img = binding.ivCategories
        var rootView = binding.root
    }


}