package com.example.android.mydemoapp.ui.weather.future

import android.content.ClipData
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding
import com.example.android.mydemoapp.databinding.ItemFutureBinding
import kotlinx.android.synthetic.main.future_list_fragment.view.*
import kotlinx.android.synthetic.main.item_future.view.*

class FutureListAdapter():
    ListAdapter<FutureWeatherParcel, FutureViewHolder>(FutureDiffCallback()) {

//    val differ = AsyncListDiffer(this, FutureDiffCallback())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureViewHolder {
        return FutureViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FutureViewHolder, position: Int) {
        holder.bind(getItem(position))
//        val article = differ.currentList[position]
        holder.itemView.apply {

                item_max_temp.text = currentList[position].maxTemp.toString()
                item_min_temp.text = currentList[position].minTemp.toString()
                item_description.text = currentList[position].description

        }
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
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: FutureWeatherParcel, newItem: FutureWeatherParcel): Boolean {
        return oldItem == newItem
    }
}