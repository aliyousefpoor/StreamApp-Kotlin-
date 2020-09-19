package com.example.streamappkotlin.productlist

import androidx.recyclerview.widget.DiffUtil
import com.example.streamappkotlin.model.Product

class ProductListDiffCallback(
    private var oldProductList: List<Product>,
    private var newProductList: List<Product>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldProductList.size
    }

    override fun getNewListSize(): Int {
        return newProductList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].id== newProductList[newItemPosition].id   }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList:Product= oldProductList[oldItemPosition]
        val newList:Product=newProductList[newItemPosition]
        return oldList.name == newList.name
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}