package com.example.propertymanagementapp.ui.main.view.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapp.data.model.RegisterRequest
import com.example.propertymanagementapp.databinding.ActivityRegisterBinding
import com.example.propertymanagementapp.ui.main.viewmodel.RegisterViewModel
import com.example.propertymanagementapp.utils.Config
import com.example.propertymanagementapp.utils.Validations
import retrofit2.Retrofit
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        DaggerComponent.builder().userModule(UserModule()).build().inject(this)

        var toolbar = binding.registerToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        val accountType = arrayOf(Config.TENANT, Config.LANDLORD)
        val accountTypeAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, accountType)
        binding.inputAccountType.setAdapter(accountTypeAdapter)
        binding.btnRegister.setOnClickListener(this)
        binding.textLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnRegister -> {
                val type = binding.inputAccountType.text.toString()
                val name = binding.inputName.text.toString()
                val email = binding.inputEmail.text.toString()
                val password = binding.inputPassword.text.toString()

                var hasError = false

                if (name.isEmpty()) {
                    hasError = true
                    binding.inputName.error = Config.ERROR_EMPTY_NAME_MESSAGE
                }

                if (email.isEmpty()) {
                    hasError = true
                    binding.inputEmail.error = Config.ERROR_EMPTY_EMAIL_MESSAGE
                }

                if (password.isEmpty()) {
                    hasError = true
                    binding.inputPassword.error = Config.ERROR_EMPTY_PASSWORD_MESSAGE

                } else {
                    if (!Validations.isValidPassword(password)) {
                        hasError = true
                        binding.inputPassword.error = Config.ERROR_PASSWORD_LENGTH_MESSAGE
                    }
                }

                if (hasError) {
                    return
                }

                val progressBar = ProgressDialog(this)
                progressBar.setMessage("Processing your request. Please wait")
                progressBar.setCancelable(true)

                val registerUser = RegisterRequest(type, name, email, password)

                registerViewModel.getRegisteredUsers(registerUser).observe(this,
                    { registerResponse ->
                        if (!registerResponse.error)
                            startActivity(Intent(this, LoginActivity::class.java))
                        else
                            Toast.makeText(
                                applicationContext,
                                Config.ERROR_MESSAGE,
                                Toast.LENGTH_SHORT
                            ).show()
                    })
                progressBar.show()
            }

            binding.textLogin -> startActivity(Intent(this, LoginActivity::class.java))

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
