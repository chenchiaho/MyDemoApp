package com.example.android.mydemoapp.ui.weather.future

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding
import com.example.android.mydemoapp.databinding.ItemFutureBinding

class FutureListAdapter():
    ListAdapter<FutureWeatherParcel, FutureViewHolder>(FutureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureViewHolder {
        return FutureViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FutureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FutureViewHolder(private val binding: ItemFutureBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(futureWeatherParcel: FutureWeatherParcel) {
        binding.future = futureWeatherParcel
    }

    companion object {
        fun from (parent: ViewGroup): FutureViewHolder {
            val binding =
                ItemFutureBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            return FutureViewHolder(binding)
        }
    }
}

class FutureDiffCallback : DiffUtil.ItemCallback<FutureWeatherParcel>() {

    override fun areItemsTheSame(oldItem: FutureWeatherParcel, newItem: FutureWeatherParcel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FutureWeatherParcel, newItem: FutureWeatherParcel): Boolean {
        return oldItem == newItem
    }
}