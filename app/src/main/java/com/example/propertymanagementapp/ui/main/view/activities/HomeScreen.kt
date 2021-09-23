package com.example.propertymanagementapp.ui.main.view.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.data.model.HomeIcons
import com.example.propertymanagementapp.databinding.ActivityHomeScreenBinding
import com.example.propertymanagementapp.ui.main.adapter.HomeIconsAdapter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class HomeScreen : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var homeIconsAdapter: HomeIconsAdapter
    private lateinit var iconsData: ArrayList<HomeIcons>
    private var pieChart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var toolbar = binding.homeToolbar
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pieChart = binding.pieChart
        setupPieChart()
        loadPieChartData()

        init()

        setAdapter()
    }

    private fun setAdapter() {
        iconsData = ArrayList()
        homeIconsAdapter = HomeIconsAdapter(this,iconsData)
        binding.rvHomeIcons.layoutManager = GridLayoutManager(this,3, RecyclerView.VERTICAL,
            false )
        binding.rvHomeIcons.adapter = homeIconsAdapter

        generateData()
    }

    private fun generateData(){
        iconsData.add(HomeIcons(R.drawable.ic_baseline_add_alert_24,"Alert"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_house_24,"Property"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_tenants_24,"Tenants"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_credit_card_24,"Transactions"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_money_24,"Rent"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_check_24,"To-Do list"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_local_taxi_24,"Trip"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_article_24,"Documents"))
        iconsData.add(HomeIcons(R.drawable.ic_baseline_vendors_alt_24,"Vendors"))
    }

    private fun init(){
        binding.textLogout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.textLogout -> startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun setupPieChart() {
        pieChart!!.description.isEnabled = false
        val l = pieChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
    }

    private fun loadPieChartData() {
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(28.1f,"Utilities"))
        entries.add(PieEntry(35.2f,"Mortgage Interest"))
        entries.add(PieEntry(36.7f,"Property"))

        val colors: ArrayList<Int> = ArrayList()

        for (color in ColorTemplate.COLORFUL_COLORS) {
            colors.add(color)
        }
        val dataSet = PieDataSet(entries,null)
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        pieChart!!.data = data
        pieChart!!.animate()
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