package com.example.streamappkotlin.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.R
import com.example.streamappkotlin.moretab.MoreItemListener
import java.util.ArrayList

class MoreAdapter(
    private var context: Context,
    private var moreList: ArrayList<MoreModel>,
    private var moreItemListener: MoreItemListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.more_adapter, parent, false)
        return MoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moreList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val moreViewHolder: MoreViewHolder = holder as MoreViewHolder

        moreViewHolder.onBind(context, moreList[position], moreItemListener)

    }

    class MoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.moreCardView)
        var title: TextView = itemView.findViewById(R.id.moreTitle)

        fun onBind(context: Context, moreModel: MoreModel, moreItemListener: MoreItemListener) {
            title.text = moreModel.title
            cardView.setOnClickListener {
                moreItemListener.onClick(moreModel)

            }
        }
    }
}