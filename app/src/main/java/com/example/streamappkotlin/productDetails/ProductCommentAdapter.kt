package com.example.streamappkotlin.productDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.Comment

class ProductCommentAdapter(private var context: Context, private var commentList: List<Comment>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.product_comment_adapter, parent, false)
        return ProductCommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val commentViewHolder = holder as ProductCommentViewHolder
        commentViewHolder.onBind(commentList[position])
    }

    class ProductCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val commentText: TextView = itemView.findViewById(R.id.commentText)
        fun onBind(commentList: Comment) {
            commentText.text = commentList.comment_text
        }
    }
}