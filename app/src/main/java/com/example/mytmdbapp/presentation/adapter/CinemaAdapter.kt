package com.example.mytmdbapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.ItemCinemaBinding
import com.example.mytmdbapp.presentation.movie.ui_model.CinemaItemUI

class CinemaAdapter(private val movieDetailed: (id: Int) -> Unit) :
    RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {

    private val differ = AsyncListDiffer(this, CinemaDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        return CinemaViewHolder(
            ItemCinemaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            movieDetailed
        )
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateCinemaList(newCinemaListUI: List<CinemaItemUI>) {
        differ.submitList(newCinemaListUI)
    }

    class CinemaViewHolder(
        private val itemCinemaBinding: ItemCinemaBinding,
        private val movieDetailed: (id: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemCinemaBinding.root) {

        private var id: Int? = null

        init {
            itemView.setOnClickListener {
                id?.let {
                    movieDetailed(it)
                }
            }
        }

        fun bind(cinemaItemUI: CinemaItemUI) {
            id = cinemaItemUI.id
            with(itemCinemaBinding) {
                movieTitle.text = cinemaItemUI.title
                movieDate.text = cinemaItemUI.date
                ratingItem.movieRatingProgress.progress = cinemaItemUI.rating
                ratingItem.movieRating.text =
                    itemView.context.getString(R.string.rating, cinemaItemUI.rating.toString())

                Glide.with(itemView)
                    .load("${itemView.context.getString(R.string.image_url)}${cinemaItemUI.poster}")
                    .placeholder(R.drawable.ic_downloading_24)
                    .error(R.drawable.ic_error_download_24).into(movieIcon)
            }
        }
    }

    class CinemaDiffUtilCallback : DiffUtil.ItemCallback<CinemaItemUI>() {
        override fun areItemsTheSame(oldItemUI: CinemaItemUI, newItemUI: CinemaItemUI): Boolean {
            return oldItemUI.id == newItemUI.id
        }

        override fun areContentsTheSame(oldItemUI: CinemaItemUI, newItemUI: CinemaItemUI): Boolean {
            return oldItemUI == newItemUI
        }
    }
}
