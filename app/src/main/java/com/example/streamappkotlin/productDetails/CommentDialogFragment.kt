package com.example.streamappkotlin.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.streamappkotlin.R

class CommentDialogFragment(private var productId: Int, private var title: String) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comment_dialog_fragment, container, false)
    }

}