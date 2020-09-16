package com.example.streamappkotlin.login

import android.app.ProgressDialog
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.login.di.LoginModule
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.utils.AppConstants

class LoginStepOneDialogFragment(private var loginStepTwoListener: LoginStepTwoListener) : DialogFragment() {
    private lateinit var number: EditText
    private lateinit var submit: Button
    private var shareViewModel: LoginShareViewModel? = null
    private var retrofit = CustomApp.instance.appModule.provideRetrofit()
    private var apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit)
    private var apiService = ApiBuilderModule.provideApiService(apiBuilder)
    private var loginRepository = LoginModule.provideLoginRepository(apiService)
    private var shareViewModelFactory =
        LoginModule.provideLoginShareViewModelFactory(loginRepository)
    private lateinit var dialog: ProgressDialog
    private lateinit var androidId: String
    private var deviceModel = AppConstants.deviceModel
    private var deviceOs = AppConstants.getAndroidVersion()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
        return inflater.inflate(R.layout.login_step_one_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shareViewModel = ViewModelProviders.of(requireActivity(), shareViewModelFactory)
            .get(LoginShareViewModel::class.java)

        number = view.findViewById(R.id.edit_txt)
        submit = view.findViewById(R.id.submit)

        loginStepOneResponse()

        submit.setOnClickListener {
            val loginStepOneRequest =
                LoginStepOneRequest(number.text.toString(), androidId, deviceModel, deviceOs)
            shareViewModel!!.loginStepOne(loginStepOneRequest)

            dialog = ProgressDialog(context)
            dialog.setTitle(R.string.progressDialogTitle);
            dialog.setMessage(getString(R.string.loadingProgress));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
    }

    private fun loginStepOneResponse() {
        shareViewModel!!.loginStepOneLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val loginStepTwoDialogFragment = LoginStepTwoDialogFragment(loginStepTwoListener)
                loginStepTwoDialogFragment.show(parentFragmentManager, "LoginStepTwoDialogFragment")
                dismiss()
                dialog.dismiss()
            } else {
                dialog.dismiss()
                Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_LONG).show()
            }
        })
    }
}