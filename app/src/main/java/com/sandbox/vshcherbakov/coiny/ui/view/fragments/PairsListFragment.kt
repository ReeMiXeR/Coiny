package com.sandbox.vshcherbakov.coiny.ui.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sandbox.vshcherbakov.coiny.R
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.AdapterDelegatesManager
import com.sandbox.vshcherbakov.coiny.ui.view.adapters.BaseViewHolder
import com.sandbox.vshcherbakov.coiny.ui.view.delegates.CurrencyPairAdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view.delegates.TitleAdapterDelegate
import com.sandbox.vshcherbakov.coiny.ui.view_model.PairsListState
import com.sandbox.vshcherbakov.coiny.ui.view_model.PairsListViewModel
import kotlinx.android.synthetic.main.fragment_pairs_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class PairsListFragment : BaseFragment() {

    private val pairsListViewModel: PairsListViewModel by viewModel()

    companion object {
        fun newInstance() = PairsListFragment()
    }

    private val adapter = AdapterDelegatesManager(
            listOf(
                    CurrencyPairAdapterDelegate(),
                    TitleAdapterDelegate()
            ) as List<AdapterDelegate<BaseViewHolder>>)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pairs_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currency_pairs_list.adapter = this.adapter

        currency_pairs_list.layoutManager = LinearLayoutManager(requireContext())


        pairsListViewModel.state.observe(this, Observer { event ->
            event?.let {
                when (it) {
                    PairsListState.Loading -> {
                        Log.e("asd", "asdad")
                    }
                    PairsListState.Empty -> TODO()
                    PairsListState.Stub -> TODO()
                    is PairsListState.Content -> {
                        adapter.addAll(it.currencyPairs)

                        Log.e("Fragment", "is main thread - ${Looper.getMainLooper() == Looper.myLooper()}")
                    }
                }
            }
        })

        pairsListViewModel.loadPairsList()
    }
}
