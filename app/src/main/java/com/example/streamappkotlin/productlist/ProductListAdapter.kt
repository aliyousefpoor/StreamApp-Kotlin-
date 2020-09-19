package com.example.streamappkotlin.productlist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.model.Product
import java.util.ArrayList

class ProductListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var products = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun addList(productLists: List<Product>) {
        val diffCallback=ProductListDiffCallback(products,productLists)
      val diffResult:DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        products= productLists as ArrayList<Product>
        diffResult.dispatchUpdatesTo(this)
    }
}