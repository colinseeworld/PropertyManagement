package com.example.propertymanagementapp.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.model.HomeIcons
import com.example.propertymanagementapp.databinding.RowHomeItemBinding
import com.example.propertymanagementapp.ui.main.view.activities.NotificationActivity
import com.example.propertymanagementapp.ui.main.view.activities.PropertyActivity
import com.example.propertymanagementapp.ui.main.view.activities.TenantActivity

class HomeIconsAdapter(private val mContext: Context, private val iconList: List<HomeIcons>): RecyclerView.Adapter<HomeIconsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowHomeItemBinding>(inflater,R.layout.row_home_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = iconList[position]
        holder.icons.mIconList = list
        holder.icons.iconImg.setImageResource(list.iconImage)
        holder.icons.root.setOnClickListener { v ->
            if (position === 0) {
                val intent = Intent(v.context, NotificationActivity::class.java)
                mContext.startActivity(intent)
            } else if (position === 1) {
                val intent = Intent(v.context, PropertyActivity::class.java)
                mContext.startActivity(intent)
            } else if (position === 2) {
                val intent = Intent(v.context, TenantActivity::class.java)
                mContext.startActivity(intent)
            } else if (position === 3) {
                //
            }
        }
    }

    override fun getItemCount() = iconList.size

    inner class ViewHolder(val icons: RowHomeItemBinding): RecyclerView.ViewHolder(icons.root)
}