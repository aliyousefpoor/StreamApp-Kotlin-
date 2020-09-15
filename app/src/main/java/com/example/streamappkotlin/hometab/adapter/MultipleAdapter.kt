package com.example.streamappkotlin.hometab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.streamappkotlin.R
import com.example.streamappkotlin.hometab.ProductListener
import com.example.streamappkotlin.model.HomeItem
import com.example.streamappkotlin.model.Product
import java.lang.IllegalArgumentException

class MultipleAdapter(
    private val context: Context, private var homeList: List<HomeItem>, private var headerList: List<Product>,var productListener: ProductListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var viewPagerType: Int = 1
    private var horizontalListType: Int = 2


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            viewPagerType
        } else {
            horizontalListType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            viewPagerType -> {
                val viewPager: View = inflater.inflate(R.layout.header_item_layout, parent, false)
                ViewPagerViewHolder(
                    viewPager
                )

            }
            horizontalListType -> {
                val listView: View = inflater.inflate(R.layout.home_item_layout, parent, false)
                ListViewHolder(
                    listView
                )
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun getItemCount(): Int {
        return homeList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            viewPagerType -> {
                val pagerHolder: ViewPagerViewHolder = holder as ViewPagerViewHolder
                pagerHolder.onBind(headerList, context)
                true
            }
            horizontalListType -> {
                val listHolder: ListViewHolder = holder as ListViewHolder
                listHolder.onBind(homeList.get(position - 1), context,productListener)
                true
            }
        }
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewPager: ViewPager = itemView.findViewById(R.id.vp_img)

        init {
            viewPager.rotationY = 180F
        }

        fun onBind(headerList: List<Product>, context: Context) {
            viewPager.adapter = ViewPagerAdapter(headerList, context)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productRecyclerView: RecyclerView = itemView.findViewById(R.id.product_rv)
        var title: TextView = itemView.findViewById(R.id.title)

        fun onBind(
            homeList: HomeItem,
            context: Context,
            productListener: ProductListener
        ) {
            title.text = homeList.getTitle()
            val adapter = ProductAdapter(homeList.getProducts(), context ,productListener)
            productRecyclerView.adapter = adapter
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            productRecyclerView.layoutManager = linearLayoutManager
            productRecyclerView.setHasFixedSize(true)
        }
    }
}