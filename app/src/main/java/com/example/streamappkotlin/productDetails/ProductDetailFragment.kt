package com.example.streamappkotlin.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.R

class ProductDetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var avatar: ImageView
    private lateinit var productName: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var play: ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var productId: Int = requireArguments().getInt("productId")

        avatar = view.findViewById(R.id.productAvatar)
        productName = view.findViewById(R.id.productName)
        play = view.findViewById(R.id.playIcon)
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.commentRecyclerView)

    }

}