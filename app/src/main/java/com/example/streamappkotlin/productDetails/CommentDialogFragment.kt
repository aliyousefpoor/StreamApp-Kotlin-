package com.example.streamappkotlin.productDetails

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.model.SendComment
import com.example.streamappkotlin.productlist.di.ProductModule

class CommentDialogFragment(private var productId: Int, private var title: String) :
    DialogFragment() {
    private lateinit var comment: EditText
    private lateinit var submit: Button
    private lateinit var dialog: ProgressDialog
    private lateinit var commentViewModel: SendCommentViewModel
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var commentViewModelFactory =
        ProductModule.provideSendCommentViewModelFactory(apiService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comment_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentViewModel = ViewModelProviders.of(this, commentViewModelFactory)
            .get(SendCommentViewModel::class.java)

        comment = view.findViewById(R.id.commentEditText)
        submit = view.findViewById(R.id.submitComment)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingPost)

        submit.setOnClickListener {
            val score: Int = ratingBar.rating.toInt()
            val sendComment =
                SendComment(title, score, comment.text.toString(), productId)

            commentViewModel.sendComment(sendComment)

            Toast.makeText(requireContext(),"Comment Sent", Toast.LENGTH_SHORT).show()

        }

        commentViewModel.commentResponse.observe(viewLifecycleOwner, Observer {
            dismiss()
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }

}