package com.planatech.embertask.common

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.planatech.embertask.R
import java.text.SimpleDateFormat

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context).load(imageUrl).apply(
        RequestOptions().placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    ).into(imageView)
}

@BindingAdapter("setAdapter")
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

@BindingAdapter("bindServerDate")
fun bindServerDate(textView: TextView, date: String?) {
    val fromServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val myFormat = SimpleDateFormat("dd, MMMM yy")
    textView.text = myFormat.format(fromServer.parse(date)).toString()
}

@BindingAdapter("setSpinnerAdapter")
fun MaterialAutoCompleteTextView.bindAdapter(adapter: ArrayAdapter<*>?) {
    this.run {
        this.setAdapter(adapter)
    }
}