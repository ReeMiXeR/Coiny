package com.sandbox.vshcherbakov.coiny.ui.view.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_common_title.view.*

class TitleAdapterDelegate : AdapterDelegate<CommonTitleViewHolder>() {
    override fun isItemDelegate(item: AdapterItem) = item is CommonTitle

    override fun getLayoutId() = R.layout.list_item_common_title

    override fun onCreateViewHolder(parent: ViewGroup): CommonTitleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), null)
        return CommonTitleViewHolder(view)
    }

    override fun onBindViewHolder(vh: CommonTitleViewHolder, item: AdapterItem, payload: List<Any>) {
        item as CommonTitle

        vh.title.text = item.title
    }
}

class CommonTitleViewHolder(view: View) : BaseViewHolder(view) {
    val title = view.title
}

class CommonTitle(val title: String) : AdapterItem