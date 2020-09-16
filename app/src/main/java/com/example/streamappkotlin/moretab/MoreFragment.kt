package com.example.streamappkotlin.moretab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.MoreAdapter
import com.example.streamappkotlin.model.MoreModel
import com.example.streamappkotlin.model.Type
import java.util.ArrayList

class MoreFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)

        val moreList = fillWithData()
        val moreAdapter = MoreAdapter(requireContext(), moreList, object : MoreItemListener {
            override fun onClick(item: MoreModel) {
                when (item.type) {
                    Type.Profile -> {
                        true
                    }
                    Type.About -> {
                        true
                    }
                    Type.Contact -> {
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        })
        recyclerView.adapter = moreAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun fillWithData(): ArrayList<MoreModel> {
        var moreLists: ArrayList<MoreModel> = ArrayList()
        moreLists.add(MoreModel("پروفایل", Type.Profile))
        moreLists.add(MoreModel("درباره ما", Type.About))
        moreLists.add(MoreModel("تماس با ما", Type.Contact))

        return moreLists
    }
}

private fun <E> ArrayList<E>.add(element: MoreModel) {

}

