package com.example.propertymanagementapp.ui.main.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapp.data.model.PropertyRequest
import com.example.propertymanagementapp.databinding.ActivityPropertyBinding
import com.example.propertymanagementapp.ui.main.viewmodel.PropertyViewModel
import com.example.propertymanagementapp.utils.FileUtils.uri2File

class PropertyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropertyBinding
    private val viewModel: PropertyViewModel by viewModels()
    private var selectUri:Uri?=null
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                val intent = Intent()
                intent.action = Intent.ACTION_GET_CONTENT
                intent.type = "image/*"
                selectTakePicture.launch(intent)
            } else {
                Toast.makeText(
                    baseContext,
                    "Cannot select images without permission grant",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val selectTakePicture: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it?.let {
                if (it.resultCode == Activity.RESULT_OK && null != it.data) {
                    val uri = it.data!!.data
                    uri?.let {
                        selectUri =it
                    }

                }
            }

        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyBinding.inflate(layoutInflater)
        binding.data =viewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        var toolbar = binding.propertyToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.toast.observe(this){
            it?.let{
                Toast.makeText(
                    baseContext,
                    it,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        init()

    }


    private fun init() {

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

    fun onClick(view: View) {
        when (view) {
            binding.btnAddPhoto -> {
                //READ_EXTERNAL_STORAGE
                requestPermission.launch(Manifest.permission.CAMERA)
            }
            binding.btnSaveAddTenants -> {
                if (selectUri!=null){
                    uri2File(selectUri!!,this)?.let { file ->
                        viewModel.imageLiveData.value = file
                        var propertyRequest = PropertyRequest(binding.etAddress.text.toString(),binding.etCity.text.toString() ,binding.etState.text.toString(),binding.etCountry.text.toString(),binding.etZipcode.text.toString(),binding.radioBtnUnits.isChecked,binding.etPurchasePrice.text.toString(),binding.radioBtnDashboard.isChecked,"",binding.radioBtnMortgage.isChecked)
                        viewModel.addProperty(propertyRequest)
                    }
                }

            }
        }
    }
}