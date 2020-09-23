package com.example.streamappkotlin.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.streamappkotlin.CustomApp
import com.example.streamappkotlin.R
import com.example.streamappkotlin.di.ApiBuilderModule
import com.example.streamappkotlin.login.di.LoginModule
import com.example.streamappkotlin.model.User
import com.example.streamappkotlin.utils.AppConstants
import com.example.streamappkotlin.utils.CalendarUtils
import com.example.streamappkotlin.utils.FileUtils
import java.io.File

class ProfileFragment : Fragment() {
    companion object {
        private val IMAGE_PICK_CODE: Int = 100
        private val IMAGE_CAPTURE_CODE: Int = 200
        private val CAMERA_PERMISSION_CODE: Int = 201

    }

    private lateinit var imageUri: Uri
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
    private lateinit var profileViewModel: ProfileViewModel
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

        profileViewModel.getUser.observe(viewLifecycleOwner, Observer {
            user = it
            progressBar.visibility = View.GONE
            name.setText(it.name)
            date.setText(it.date)
            user.token = it.token
            Glide.with(requireContext()).load(AppConstants.baseUrl+user.avatar).into(avatar)
            val checkGender: String = it.gender
            if (checkGender == "Male") {
                male.isChecked = true
            } else {
                female.isChecked = true
            }

        })
        avatar.setOnClickListener {
            setProfileImage()
        }
        date.setOnClickListener {
            showCalendar()
        }
        profileViewModel.getProfile()


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

            profileViewModel.updateProfile(users)
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

    private fun setProfileImage() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Choose Profile Image")
        builder.setCancelable(true)

        builder.setPositiveButton("Gallery", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                pickImageFromGallery()
                dialog!!.dismiss()
            }

        })

        builder.setNegativeButton("Camera", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_DENIED ||
                        ActivityCompat.checkSelfPermission(
                            requireContext()
                            , android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_DENIED
                    ) {
                        val permissions = arrayOf(
                            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        requestPermissions(permissions, CAMERA_PERMISSION_CODE)
                    } else {
                        openCamera()
                    }

                }
                dialog!!.dismiss()

            }

        })

        builder.setNeutralButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
            }
        })


        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun pickImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        if (galleryIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = requireActivity().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )!!

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(requireContext(), "Permission Denied... !", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data!!.data!!
            avatar.setImageURI(imageUri)
            updateProfileImage(imageUri)
        } else if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_CAPTURE_CODE) {
            avatar.setImageURI(imageUri)
            updateProfileImage(imageUri)
        }
    }

    private fun updateProfileImage(uri: Uri) {
        val file: File = FileUtils.getFile(context, uri)
        profileViewModel.updateImage(user.token, file)
    }


}