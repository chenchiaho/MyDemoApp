package com.example.android.mydemoapp.ui.weather.future

import android.annotation.SuppressLint
import android.content.ClipData
import android.nfc.Tag
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding
import com.example.android.mydemoapp.databinding.ItemFutureBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.future_list_fragment.view.*
import kotlinx.android.synthetic.main.item_future.view.*

class FutureListAdapter(private val clickListener: OnClickListener):
    ListAdapter<FutureWeatherParcel, FutureViewHolder>(FutureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutureViewHolder {
        return FutureViewHolder.from(parent)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FutureViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)

        holder.itemView.apply {

            item_max_temp.text = "Max:\n${currentList[position].maxTemp}".removeRange(8, 9)
            item_min_temp.text = "Min:\n${currentList[position].minTemp}".removeRange(8, 9)
            item_description.text = currentList[position].description
            val strs = currentList[position].date.split(" ").toTypedArray()
            item_date.text = strs[0]
            item_time.text = strs[1].removeRange(4, 7)

            val imageURL = "http://openweathermap.org/img/wn/${currentList[position].icon}@2x.png"
            imageURL.let {
                val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
                Glide.with(item_icon.context)
                        .load(imgUri)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.outline_broken_image))
                        .into(item_icon)
            }

        }
    }
}

class FutureViewHolder(private val binding: ItemFutureBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(futureWeatherParcel: FutureWeatherParcel, clickListener: OnClickListener) {
        binding.future = futureWeatherParcel
        binding.clickListener = clickListener
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

class OnClickListener(private val clickHandler: (futureWeather: FutureWeatherParcel) -> Unit) {
    fun onClick(futureWeather: FutureWeatherParcel) = clickHandler(futureWeather)
}