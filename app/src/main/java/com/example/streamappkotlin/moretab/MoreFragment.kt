package com.example.streamappkotlin.moretab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.login.LoginShareViewModel
import com.example.streamappkotlin.login.LoginStepOneDialogFragment
import com.example.streamappkotlin.login.LoginStepTwoListener
import com.example.streamappkotlin.login.di.LoginModule
import com.example.streamappkotlin.model.MoreAdapter
import com.example.streamappkotlin.model.MoreModel
import com.example.streamappkotlin.model.Type
import java.util.ArrayList
import java.util.EnumSet.of

class MoreFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    private var shareViewModel: LoginShareViewModel? = null
    private lateinit var loginStepTwoListener: LoginStepTwoListener
    private var database = LoginModule.provideUserDatabase()
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var loginRepository = LoginModule.provideLoginRepository(apiService, database.userDao())
    private var shareViewModelFactory =
        LoginModule.provideLoginShareViewModelFactory(loginRepository)
    private lateinit var moreItemListener: MoreItemListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        setUpLogin()
//        itemType()
        return inflater.inflate(R.layout.more_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareViewModel = ViewModelProviders.of(requireActivity(), shareViewModelFactory)
            .get(LoginShareViewModel::class.java)

        recyclerView = view.findViewById(R.id.recycler_view)
        navController = Navigation.findNavController(view)

        val moreList = fillWithData()
        val moreAdapter = MoreAdapter(requireContext(), moreList, object : MoreItemListener {
            override fun onClick(item: MoreModel) {
                when (item.type) {
                    Type.Profile -> {
                        shareViewModel!!.isLogin()
                    }
                    Type.About -> {
                        navController.navigate(R.id.action_moreFragment2_to_aboutFragment)
                    }
                    Type.Contact -> {
                        navController.navigate(R.id.action_moreFragment2_to_contactFragment)
                    }
                }
            }

        })
        recyclerView.adapter = moreAdapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)


        shareViewModel!!.isLogin.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                navController.navigate(R.id.action_moreFragment2_to_profileFragment)
            } else {
                val loginStepOneDialogFragment =
                    LoginStepOneDialogFragment(object : LoginStepTwoListener {
                        override fun userExist(exist: Boolean) {
                            navController.navigate(R.id.action_moreFragment2_to_profileFragment)
                        }

                    })
                loginStepOneDialogFragment.show(
                    parentFragmentManager,
                    "LoginStepOneDialogFragment"
                )
            }
        })

    }

    private fun fillWithData(): ArrayList<MoreModel> {
        val moreLists: ArrayList<MoreModel> = ArrayList()
        moreLists.add(MoreModel("پروفایل", Type.Profile))
        moreLists.add(MoreModel("درباره ما", Type.About))
        moreLists.add(MoreModel("تماس با ما", Type.Contact))

        return moreLists
    }

    private fun itemType() {
        moreItemListener = object : MoreItemListener {
            override fun onClick(item: MoreModel) {
                when (item.type) {
                    Type.Profile -> {
                        shareViewModel!!.isLogin()
                    }
                    Type.About -> {
                        navController.navigate(R.id.action_moreFragment2_to_aboutFragment)
                    }
                    Type.Contact -> {
                        navController.navigate(R.id.action_moreFragment2_to_contactFragment)
                    }
                }
            }

        }
    }

    private fun setUpLogin() {
        loginStepTwoListener = object : LoginStepTwoListener {
            override fun userExist(exist: Boolean) {
                navController.navigate(R.id.action_moreFragment2_to_profileFragment)
            }

        }
    }
}

