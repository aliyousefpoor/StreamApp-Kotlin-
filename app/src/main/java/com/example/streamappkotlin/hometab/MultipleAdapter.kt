package com.example.streamappkotlin.hometab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.R
import java.lang.IllegalArgumentException

class MultipleAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
        var inflater: LayoutInflater = LayoutInflater.from(context)
       return when (viewType) {
            viewPagerType -> {
                var viewPager: View = inflater.inflate(R.layout.header_item_layout, parent, false)
               ViewPagerViewHolder(viewPager)

            }
            horizontalListType -> {
                var listView: View = inflater.inflate(R.layout.home_item_layout, parent, false)
                ListViewHolder(listView)
            }
           else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            viewPagerType -> {
                var pagerHolder: ViewPagerViewHolder = holder as ViewPagerViewHolder
                pagerHolder.onBind()
                true
            }
            horizontalListType -> {
                var listHolder: ListViewHolder = holder as ListViewHolder
                listHolder.onBind()
                true
            }
        }
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {

        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind() {

        }
    }
}