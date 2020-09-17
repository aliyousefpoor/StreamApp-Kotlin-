package com.example.streamappkotlin.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.streamappkotlin.R

class ProfileFragment : Fragment() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var male: RadioButton
    private lateinit var female: RadioButton
    private lateinit var name: EditText
    private lateinit var date: EditText
    private lateinit var submitChange: Button
    private lateinit var cancel: Button
    private lateinit var avatar: ImageView
    private lateinit var progressBar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBarLayout)
        radioGroup = view.findViewById(R.id.radio_group)
        male = view.findViewById(R.id.male)
        female = view.findViewById(R.id.female)
        name = view.findViewById(R.id.name)
        date = view.findViewById(R.id.date)
        avatar = view.findViewById(R.id.avatar)
        submitChange = view.findViewById(R.id.change)
        cancel = view.findViewById(R.id.cancle)

        progressBar.visibility = View.GONE
        radioButton(view)

        date.setOnClickListener {
            showCalendar()
        }
    }

    private fun radioButton(view: View) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioButton = view.findViewById(checkedId)
        }
    }

    private fun showCalendar(){

    }
}