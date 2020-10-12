package com.example.streamappkotlin.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.Product
import com.example.streamappkotlin.utils.AppConstants
import java.util.ArrayList

class ProductListAdapter(
    private var context: Context,
    private var productListener: ProductListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var products = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.product_list_adapter_layout, parent, false)
        return ProductListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productListViewHolder = holder as ProductListViewHolder
        productListViewHolder.onBind(products[position], context, productListener)
    }

    class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: ImageView = itemView.findViewById(R.id.productAvatar)
        var title: TextView = itemView.findViewById(R.id.productTitle)
        private var cardView: CardView = itemView.findViewById(R.id.productListCardView)

        fun onBind(
            product: Product,
            context: Context,
            productListener: ProductListener
        ) {
            title.text = product.name
            Glide.with(context).load(AppConstants.baseUrl + product.avatar.mdpi).into(avatar)

            cardView.setOnClickListener {
                productListener.onClick(product.id)
            }
        }
    }

    fun addList(productLists: List<Product>) {
        val diffCallback = ProductListDiffCallback(products, productLists)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        products = productLists as ArrayList<Product>
        diffResult.dispatchUpdatesTo(this)
    }
}