package com.example.mytmdbapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.ItemCreditsBinding
import com.example.mytmdbapp.presentation.movie.ui_model.CreditsUIModel

class CreditsAdapter : RecyclerView.Adapter<CreditsAdapter.CreditsViewHolder>() {

    private val differ = AsyncListDiffer(this, CreditsDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditsViewHolder {
        return CreditsViewHolder(
            ItemCreditsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateCreditsList(newCreditsList: List<CreditsUIModel>) {
        differ.submitList(newCreditsList)
    }

    class CreditsViewHolder(private val itemCreditsBinding: ItemCreditsBinding) :
        RecyclerView.ViewHolder(itemCreditsBinding.root) {

        fun bind(creditsUIModel: CreditsUIModel) {
            with(itemCreditsBinding) {
                castName.text = creditsUIModel.name
                castRole.text = creditsUIModel.role

                Glide.with(itemView).load("${itemView.context.getString(R.string.image_url)}${creditsUIModel.avatar}")
                    .placeholder(R.drawable.ic_downloading_24)
                    .error(R.drawable.ic_error_download_24).transform(CircleCrop()).into(castAvatar)
            }
        }
    }

    class CreditsDiffUtilCallback : DiffUtil.ItemCallback<CreditsUIModel>() {
        override fun areItemsTheSame(oldItem: CreditsUIModel, newItem: CreditsUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CreditsUIModel, newItem: CreditsUIModel): Boolean {
            return oldItem == newItem
        }

    }
}