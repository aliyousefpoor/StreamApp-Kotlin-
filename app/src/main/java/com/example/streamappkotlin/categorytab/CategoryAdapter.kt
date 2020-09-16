package com.example.streamappkotlin.categorytab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.streamappkotlin.ProductListener
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.Category
import com.example.streamappkotlin.utils.AppConstants

class CategoryAdapter(
    private var categories: List<Category>,
    private var context: Context,
    private var productListener: ProductListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.category_adapter_layout, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryViewHolder: CategoryViewHolder = holder as CategoryViewHolder
        categoryViewHolder.onBind(categories.get(position), context, productListener)
    }


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.card_view)
        var imageView: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)

        fun onBind(categories: Category, context: Context, productListener: ProductListener) {
            title.text = categories.title
            Glide.with(context).load(AppConstants.baseUrl + categories.avatar).into(imageView)
            cardView.setOnClickListener {
                productListener.onClick(categories.id)
            }
        }
    }
}