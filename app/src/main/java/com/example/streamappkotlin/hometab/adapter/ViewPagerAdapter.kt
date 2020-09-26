package com.example.streamappkotlin.hometab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.Product
import com.example.streamappkotlin.utils.AppConstants

class ViewPagerAdapter(var headerList: List<Product>,var context: Context) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return headerList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.view_pager_layout, container, false)
        val imageView: ImageView = view.findViewById(R.id.view_pager)
        Glide.with(context)
            .load(AppConstants.baseUrl + headerList[position].avatar.xhdpi)
            .into(imageView)
        imageView.setOnClickListener {
            Toast.makeText(
                container.context,
                headerList[position].name,
                Toast.LENGTH_SHORT
            ).show()

        }
        val viewPager: ViewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager: ViewPager = container as ViewPager
        val view: View = `object` as View
        viewPager.removeView(view)
    }
}