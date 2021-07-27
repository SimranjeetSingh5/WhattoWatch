package com.simranjeetsingh05.dreamzapp.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.simranjeetsingh05.dreamzapp.R
import com.simranjeetsingh05.dreamzapp.database.UserDao
import com.simranjeetsingh05.dreamzapp.database.UserDatabase
import com.simranjeetsingh05.dreamzapp.databinding.ActivitySignUpBinding
import com.simranjeetsingh05.dreamzapp.model.User
import com.simranjeetsingh05.dreamzapp.repository.UserRepository
import com.simranjeetsingh05.dreamzapp.viewmodel.UserViewModel
import com.simranjeetsingh05.dreamzapp.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private val REQUESTCODE = 100
    private lateinit var bitmap: Bitmap
    private var cal = Calendar.getInstance()
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firstName:String
    private lateinit var lastName:String
    private lateinit var dob: String
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var image: ByteArray

    private lateinit var viewModel: UserViewModel
    private lateinit var userDatabase: UserDatabase
    private lateinit var repository: UserRepository
    private lateinit var factory: UserViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabase = UserDatabase.getInstance(this)
        repository = UserRepository(userDatabase)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

//        For Getting image from gallery
        binding.userImageView.setOnClickListener {
            openGalleryForImage()
        }
//        For Action bar text change view different intent
        if(intent!=null){
            val actionBarText = intent.getStringExtra("actionBarText")
            if(actionBarText.equals("Create Account")){
                binding.actionBar.text = getString(R.string.create)
            }
            else{
                binding.actionBar.text = getString(R.string.update)
            }
        }
//      Date of Birth Dialog Set Method Call
        calenderSet()

//        Submit button onClick
        binding.submitButton.setOnClickListener {
                firstName = binding.firstNameET.text.toString()
                lastName = binding.lastNameET.text.toString()
                dob = binding.dobButton.text.toString()
                userName = binding.usernameET.text.toString()
                password = binding.passwordET.text.toString()

//                validateUser()
                var id = 1
                val user = User(
                    uid = id++, firstName = firstName, lastName = lastName, dob = dob,
                    image = image, username = userName, password = password
                )
                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.insertUser(user)
                        Toast.makeText(
                            applicationContext,
                            "User entered in database $firstName + $lastName",
                            Toast.LENGTH_LONG
                        ).show()
                        intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }
            }
    }

//    private fun validateUser() {
//        when {
//            TextUtils.isEmpty(firstName) -> {
//                val toast = Toast.makeText(this,"First Name Can't be empty",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            TextUtils.isEmpty(lastName) -> {
//                val toast = Toast.makeText(this,"Last Name Can't be empty",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            TextUtils.isEmpty(dob) -> {
//                val toast = Toast.makeText(this,"DOB Can't be empty",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            TextUtils.isEmpty(userName) -> {
//                val toast = Toast.makeText(this,"Username Can't be empty",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            image.isEmpty() -> {
//                val toast = Toast.makeText(this,"Please select a profile image",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            TextUtils.isEmpty(password) -> {
//                val toast = Toast.makeText(this,"Password Can't be empty",Toast.LENGTH_LONG)
//                toast.show()
//                return
//            }
//            else -> {
//                return
//            }
//        }
//    }

    private fun calenderSet(){
        // create an OnDateSetListener
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
        binding.dobButton.setOnClickListener {
            DatePickerDialog(this@SignUpActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.dobButton.text = sdf.format(cal.time)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUESTCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            val imageUri: Uri = data!!.data!!
            try {
                val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
                bitmap = BitmapFactory.decodeStream(inputStream)
                val blob = ByteArrayOutputStream()
                bitmap.compress(CompressFormat.JPEG, 98 /* Ignored for PNGs */, blob)
                val bitmapdata: ByteArray = blob.toByteArray()
                image= bitmapdata
                binding.userImageView.setImageURI(imageUri)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }


}







