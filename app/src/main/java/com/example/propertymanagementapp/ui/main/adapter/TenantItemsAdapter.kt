package com.example.propertymanagementapp.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.model.TenantItems
import com.example.propertymanagementapp.databinding.RowTenantItemBinding
import com.example.propertymanagementapp.ui.main.view.activities.AddTenantActivity

class TenantItemsAdapter (private val mContext: Context, private val itemsList: List<TenantItems>): RecyclerView.Adapter<TenantItemsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowTenantItemBinding>(inflater,R.layout.row_tenant_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.items.mTenantName = itemsList[position]
        holder.items.root.setOnClickListener { v ->
            if (position === 0) {
                val intent = Intent(v.context, AddTenantActivity::class.java)
                mContext.startActivity(intent)
            } else if (position === 1) {
                //
            }
        }
    }

    override fun getItemCount() = itemsList.size

    inner class ViewHolder(val items: RowTenantItemBinding): RecyclerView.ViewHolder(items.root)
}