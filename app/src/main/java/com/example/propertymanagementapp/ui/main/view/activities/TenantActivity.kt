package com.example.propertymanagementapp.ui.main.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagementapp.data.model.TenantItems
import com.example.propertymanagementapp.databinding.ActivityTenantBinding
import com.example.propertymanagementapp.ui.main.adapter.TenantItemsAdapter

class TenantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenantBinding
    private lateinit var tenantItemsAdapter: TenantItemsAdapter
    private lateinit var itemsData: ArrayList<TenantItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toolbar = binding.tenantsToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setAdapter()
    }

    private fun setAdapter() {
        itemsData = ArrayList()
        tenantItemsAdapter = TenantItemsAdapter(this,itemsData)
        binding.rvTenantItems.layoutManager = LinearLayoutManager(this)
        binding.rvTenantItems.adapter = tenantItemsAdapter

        generateData()
    }

    private fun generateData(){
        itemsData.add(TenantItems("Add New"))
        itemsData.add(TenantItems("Contact Tenants"))
        itemsData.add(TenantItems("Applicants"))
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