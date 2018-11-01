package com.sandbox.vshcherbakov.coiny.ui.view.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.domain.model.CurrencyPair
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterItem
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_currency_pair.view.*

class CurrencyPairAdapterDelegate : AdapterDelegate<CurrencyPairViewHolder>() {
    override fun isItemDelegate(item: AdapterItem) = item is CurrencyPair

    override fun getLayoutId() = R.layout.list_item_currency_pair

    override fun onCreateViewHolder(parent: ViewGroup): CurrencyPairViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), null)
        return CurrencyPairViewHolder(view)
    }

    override fun onBindViewHolder(vh: CurrencyPairViewHolder, item: AdapterItem, payload: List<Any>) {
        item as CurrencyPair

        vh.bind(item)
    }
}

class CurrencyPairViewHolder(view: View) : BaseViewHolder(view) {
    val fromTo: TextView = view.currency_pair_from_to
    val pairPrice: TextView = view.currency_pair_price
    val changeDay: TextView = view.currency_pair_change_day
    val market: TextView = view.currency_pair_martket

    fun bind(item: CurrencyPair) {
        fromTo.text = "${item.FROMSYMBOL}/${item.TOSYMBOL}"
        pairPrice.text = item.PRICE
        changeDay.text = item.CHANGE24HOUR
        market.text = item.MARKET
    }
}
