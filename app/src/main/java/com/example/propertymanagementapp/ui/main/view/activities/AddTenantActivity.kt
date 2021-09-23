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
import com.example.propertymanagementapp.data.model.TenantRequest
import com.example.propertymanagementapp.databinding.ActivityAddTenantBinding
import com.example.propertymanagementapp.ui.main.viewmodel.AddTenantViewModel
import com.example.propertymanagementapp.utils.Config
import com.example.propertymanagementapp.utils.Validations

class AddTenantActivity : AppCompatActivity(),View.OnClickListener{

    private lateinit var binding: ActivityAddTenantBinding
    private val tenantViewModel: AddTenantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTenantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toolbar = binding.addTenantsToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init(){
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnSubmit -> {
                val propertyId = binding.inputPropertyId.text.toString()
                val email = binding.inputEmail.text.toString()
                val name = binding.inputName.text.toString()

                var hasError = false

                if(email.isEmpty()){
                    hasError = true
                    binding.inputEmail.error = Config.ERROR_EMPTY_EMAIL_MESSAGE
                }

                if(name.isEmpty()){
                    hasError = true
                    binding.inputName.error = Config.ERROR_EMPTY_NAME_MESSAGE

                }

                if (propertyId.isEmpty()) {
                    hasError = true
                    binding.inputPropertyId.error = Config.ERROR_EMPTY_PROPERTY_ID

                } else {
                    if (!Validations.isValidPropertyId(propertyId)) {
                        hasError = true
                        binding.inputPropertyId.error = Config.ERROR_PASSWORD_LENGTH_PROPERTYID
                    }
                }

                if(hasError){
                    return
                }

                val progressBar = ProgressDialog(this)
                progressBar.setMessage("Processing your request. Please wait")
                progressBar.setCancelable(true)

                val addTenant = TenantRequest(propertyId,email, name)

                tenantViewModel.addTenants(addTenant).observe(this,
                    { tenantResponse ->
                        if (!tenantResponse.error!!){
                            Toast.makeText(baseContext,"Add successfully",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,TenantActivity::class.java))
                        } else
                            Toast.makeText(applicationContext, Config.ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                    })
                progressBar.show()
            }
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