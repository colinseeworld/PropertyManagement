package com.example.propertymanagementapp.ui.main.view.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapp.data.model.LoginRequest
import com.example.propertymanagementapp.databinding.ActivityLoginBinding
import com.example.propertymanagementapp.ui.main.viewmodel.LoginViewModel
import com.example.propertymanagementapp.utils.Config
import com.example.propertymanagementapp.utils.Validations

class LoginActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        DaggerComponent.builder().userModule(UserModule()).build().inject(this)

        var toolbar = binding.loginToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init(){
        binding.btnLogin.setOnClickListener(this)
        binding.textRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v){
           binding.btnLogin -> {
               val email = binding.inputEmail.text.toString()
               val password = binding.inputPassword.text.toString()

               var hasError = false

               if(email.isEmpty()){
                   hasError = true
                   binding.inputEmail.error = Config.ERROR_EMPTY_EMAIL_MESSAGE
               }

               if(password.isEmpty()){
                   hasError = true
                   binding.inputPassword.error = Config.ERROR_EMPTY_PASSWORD_MESSAGE

               }else{
                   if(!Validations.isValidPassword(password)){
                       hasError = true
                       binding.inputPassword.error = Config.ERROR_PASSWORD_LENGTH_MESSAGE
                   }
               }

               if(hasError){
                   return
               }

               val progressBar = ProgressDialog(this)
               progressBar.setMessage("Processing your request. Please wait")
               progressBar.setCancelable(true)

               val loginUser = LoginRequest(email, password)

               loginViewModel.getLoginUsers(loginUser).observe(this,
                   { loginResponse ->
                       if (!loginResponse.error!!)
                           startActivity(Intent(this,HomeScreen::class.java))
                       else
                           Toast.makeText(applicationContext, Config.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                   })
               progressBar.show()
           }

           binding.textRegister -> startActivity(Intent(this,RegisterActivity::class.java))

       }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}