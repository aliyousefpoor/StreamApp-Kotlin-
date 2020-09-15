package com.example.streamappkotlin.hometab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.streamappkotlin.R
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    lateinit var arrow: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var pullDown: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrow = view.findViewById(R.id.arrow)
        pullDown=view.findViewById(R.id.pullDown)
        recyclerView=view.findViewById(R.id.rec_view)
    }
}