package com.example.streamappkotlin.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.login.di.LoginModule
import com.example.streamappkotlin.model.User
import com.example.streamappkotlin.utils.CalendarUtils

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
    private lateinit var user: User
    private var profileViewModel: ProfileViewModel? = null
    private var database = LoginModule.provideUserDatabase()
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var profileViewModelFactory =
        LoginModule.provideProfileViewModelFactory(apiService, database.userDao())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel =
            ViewModelProviders.of(this, profileViewModelFactory).get(ProfileViewModel::class.java)

        progressBar = view.findViewById(R.id.progressBarLayout)
        radioGroup = view.findViewById(R.id.radio_group)
        male = view.findViewById(R.id.male)
        female = view.findViewById(R.id.female)
        name = view.findViewById(R.id.name)
        date = view.findViewById(R.id.date)
        avatar = view.findViewById(R.id.avatar)
        submitChange = view.findViewById(R.id.change)
        cancel = view.findViewById(R.id.cancle)

        radioButton(view)

        progressBar.visibility = View.VISIBLE

        profileViewModel!!.getUser.observe(viewLifecycleOwner, Observer {
            user = it
            progressBar.visibility = View.GONE
            name.setText(it.name)
            date.setText(it.date)
            user.token = it.token
            val checkGender: String = it.gender
            if (checkGender == "Male") {
                male.isChecked = true
            } else {
                female.isChecked = true
            }

        })

        date.setOnClickListener {
            showCalendar()
        }
        profileViewModel!!.getProfile()


        submitChange.setOnClickListener {
            val users = User()
            users.id = user.id
            users.token = user.token
            users.name = name.text.toString()
            users.date = date.text.toString()
            if (radioButton.text == "مرد") {
                users.gender = "Male"
            } else {
                users.gender = "Female"
            }

            profileViewModel!!.updateProfile(users)
        }
    }

    private fun radioButton(view: View) {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            radioButton = view.findViewById(checkedId)
        }
    }

    private fun showCalendar() {
        val calendarUtils = CalendarUtils(requireContext(), object : DateListener {
            override fun onDateChange(persianCalendar: String) {
                date.setText(persianCalendar)
            }
        })

        calendarUtils.showCalendar()
    }
}