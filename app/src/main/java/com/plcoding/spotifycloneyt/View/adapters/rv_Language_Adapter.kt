package com.plcoding.spotifycloneyt.View.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.databinding.HRvCategoriesBinding

class rv_Language_Adapter(
    val category: List<String>,
    val ImgList: List<Int>,
    val context: Context
) : RecyclerView.Adapter<rv_Language_Adapter.LanguageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {

        val view = HRvCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = LanguageViewHolder(view)
        return viewholder
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val _Cname = category[position]
        val _currentImg = ImgList[position]

        holder.name.text = _Cname

        Glide.with(context)
            .load(_currentImg)
            .into(holder.Img)

        holder.itemView.setOnClickListener {View->
            Snackbar.make(View, _Cname, Snackbar.LENGTH_LONG).show()
            Navigation.findNavController(View)
                .navigate(R.id.globalActionToexploreSongsFrag)
        }

    }

    override fun getItemCount(): Int {
        return category.size
    }

    class LanguageViewHolder(binding: HRvCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.tvCategory
        var Img = binding.ivCategories
        var rootView = binding.root
    }

}