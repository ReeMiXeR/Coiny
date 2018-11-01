package com.sandbox.vshcherbakov.coiny.ui.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

class AdapterDelegatesManager(
        private val delegates: List<AdapterDelegate<BaseViewHolder>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = emptyList<AdapterItem>()

    fun addAll(items: List<AdapterItem>) {
        this.items = items
        notifyItemRangeInserted(0, items.size)
    }

    override fun getItemViewType(position: Int): Int {
        val delegate = delegates.firstOrNull { it.isItemDelegate(items[position]) }
        return delegate?.getLayoutId()
                ?: throw IllegalStateException("desired adapter delegate not found")
    }

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): RecyclerView.ViewHolder {
        val delegate = delegates.firstOrNull { it.getLayoutId() == layoutId }
        return delegate?.onCreateViewHolder(parent)
                ?: throw IllegalStateException("desired adapter delegate not found")
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) { // , payloads: List<Any>
        val item = items[position] // [vh.adapterPosition]
        val delegate = delegates.firstOrNull { it.isItemDelegate(item) }
        delegate?.onBindViewHolder(vh as BaseViewHolder, item, emptyList())
                ?: throw IllegalStateException("desired adapter delegate not found")
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val item = items[position] // [vh.adapterPosition]
        val delegate = delegates.firstOrNull { it.isItemDelegate(item) }
        delegate?.onBindViewHolder(vh as BaseViewHolder, item, payloads)
                ?: throw IllegalStateException("desired adapter delegate not found")
    }

    override fun getItemCount(): Int = items.size
}



abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) // выглядит как лишняя сущность

abstract class AdapterDelegate<T : BaseViewHolder> {
    abstract fun isItemDelegate(item: AdapterItem): Boolean
    abstract fun getLayoutId(): Int
    abstract fun onCreateViewHolder(parent: ViewGroup): T // BaseViewHolder?
    abstract fun onBindViewHolder(vh: T, item: AdapterItem, payload: List<Any>)
}

interface AdapterItem