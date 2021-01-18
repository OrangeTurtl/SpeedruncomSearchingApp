package com.stepanf.speedruncomapiapp.ui.games_search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.stepanf.speedruncomapiapp.R
import com.stepanf.speedruncomapiapp.data.games.SpeedrunGame
import com.stepanf.speedruncomapiapp.databinding.ItemGameBinding

private const val TAG = "GamesAdapter"

class GamesAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<SpeedrunGame, GamesAdapter.GameViewHolder>(PHOTO_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class GameViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.OnItemClick(item)
                    }
                }
            }
        }

        fun bind(game: SpeedrunGame) {
            binding.apply {
                Glide.with(itemView)
                    .load(game.assets.cover_medium.uri)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_round_cancel_24)
                    .into(backgroundImage)

                textViewGameName.text = game.names.international
            }
        }
    }

    interface OnItemClickListener {
        fun OnItemClick(game: SpeedrunGame)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<SpeedrunGame>() {
            override fun areItemsTheSame(oldItem: SpeedrunGame, newItem: SpeedrunGame): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SpeedrunGame, newItem: SpeedrunGame): Boolean =
                oldItem == newItem
        }
    }
}