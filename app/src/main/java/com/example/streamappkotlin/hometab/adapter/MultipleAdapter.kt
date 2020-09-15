package com.example.streamappkotlin.hometab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.HomeItem
import com.example.streamappkotlin.model.Product
import java.lang.IllegalArgumentException

class MultipleAdapter(
    private val context: Context, private var homeList: List<HomeItem>
    , private var headerList: List<Product>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
                listHolder.onBind(homeList, context)
                true
            }
        }
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewPager: ViewPager = itemView.findViewById(R.id.vp_img)

        init {
            viewPager.rotationY= 180F
        }

        fun onBind(headerList: List<Product>, context: Context) {
            viewPager.adapter = ViewPagerAdapter(headerList, context)
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(homeList: List<HomeItem>, context: Context) {

        }
    }
}