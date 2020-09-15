package com.example.streamappkotlin.hometab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.Product

class ProductAdapter(var products: List<Product>, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productViewHolder: ProductViewHolder = holder as ProductViewHolder
        productViewHolder.onBind(products.get(position), context)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.product_cv)
        var imageView: ImageView = itemView.findViewById(R.id.cv_image)
        var textView: TextView = itemView.findViewById(R.id.discription)

        fun onBind(product: Product, context: Context) {
            textView.text = product.getName()
            Glide.with(context).load(product.getAvatar().getXhdpi()).into(imageView)

        }
    }
}