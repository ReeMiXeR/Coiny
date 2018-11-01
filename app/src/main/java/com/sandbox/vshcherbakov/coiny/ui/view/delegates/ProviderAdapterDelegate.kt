package com.sandbox.vshcherbakov.coiny.ui.view.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.Provider
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_provider.view.*

class ProviderAdapterDelegate : AdapterDelegate<ProviderViewHolder>() {
    override fun isItemDelegate(item: AdapterItem) = item is Provider

    override fun getLayoutId() = R.layout.list_item_provider

    override fun onCreateViewHolder(parent: ViewGroup): ProviderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), null)
        return ProviderViewHolder(view)
    }

    override fun onBindViewHolder(vh: ProviderViewHolder, item: AdapterItem, payload: List<Any>) {
        item as Provider
        vh.bind(item)
    }
}

class ProviderViewHolder(val view: View) : BaseViewHolder(view) {
    val name: TextView = view.provider_name
    val image: ImageView = view.provider_image

    fun bind(provider: Provider) {
        name.text = provider.name
        Glide.with(view.context)
                .load(provider.img)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image)
    }
}