package com.example.streamappkotlin.login

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.streamappkotlin.R
import com.example.streamappkotlin.model.LoginStepOneRequest
import com.example.streamappkotlin.model.LoginStepTwoRequest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class LoginStepTwoDialogFragment(private var loginStepTwoListener: LoginStepTwoListener) :
    DialogFragment() {
    private lateinit var submit: Button
    private lateinit var changeNumber: Button
    private lateinit var resendCode: TextView
    private lateinit var code: EditText
    private lateinit var number: String
    private lateinit var androidId: String
    private lateinit var deviceModel: String
    private lateinit var deviceOs: String
    private val shareViewModel: LoginShareViewModel by sharedViewModel()
    private lateinit var dialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_step_two_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit = view.findViewById(R.id.secondDialogSubmit)
        changeNumber = view.findViewById(R.id.changeNumber)
        resendCode = view.findViewById(R.id.resendCode)
        code = view.findViewById(R.id.verificationCode)
        loginStepTwoResponse()
        submit.setOnClickListener {
            number = shareViewModel.loginStepOneRequestBody.mobile
            androidId = shareViewModel.loginStepOneRequestBody.device_id
            val loginStepTwoRequest = LoginStepTwoRequest(number, androidId, code.text.toString())

            shareViewModel.loginStepTwo(loginStepTwoRequest)

            dialog = ProgressDialog(context);
            dialog.setTitle(R.string.progressDialogTitle);
            dialog.setMessage(getString(R.string.loadingProgress));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
        changeNumber.setOnClickListener {
            dialog.dismiss()
            val loginStepOneDialogFragment = LoginStepOneDialogFragment(loginStepTwoListener)
            loginStepOneDialogFragment.show(parentFragmentManager, "LoginStepOneDialogFragment")
        }
        resendCode.setOnClickListener {
            deviceModel = shareViewModel.loginStepOneRequestBody.device_model
            deviceOs = shareViewModel.loginStepOneRequestBody.device_os

            val loginStepOneRequest = LoginStepOneRequest(number, androidId, deviceModel, deviceOs)
            shareViewModel.loginStepOne(loginStepOneRequest)
        }
    }

    private fun loginStepTwoResponse() {
        shareViewModel.loginStepTwoLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {

                loginStepTwoListener.userExist(true)

                dismiss()
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Enter Valid Code", Toast.LENGTH_SHORT).show()

            }
        })
    }
}