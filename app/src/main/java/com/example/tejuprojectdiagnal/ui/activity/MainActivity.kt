package com.example.tejuprojectdiagnal.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.databinding.ActivityMainBinding
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import com.example.tejuprojectdiagnal.mvvm.view_model.ContentViewModel
import com.example.tejuprojectdiagnal.ui.adapter.ContentRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ContentViewModel by viewModels()

    @Inject
    lateinit var jsonHelperClass: JsonHelperClass

    private lateinit var data: ContentModel

    companion object {
        private const val TAG = "MainActivity"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.page = 0
        viewModel.clearData()

        viewModel.initPagedContentList(0)
        viewModel.getPagedContentList().observe(this, {
            data = it
            if(viewModel.page > 0) {
                binding.recycler.adapter?.notifyDataSetChanged()
            }else{
                initRecycler(it.page?.`content-items`?.content, false)
            }
        })
    }

    private fun initRecycler(
        data: ArrayList<ContentModel.Page.ContentItems.Content?>?,
        isSearch: Boolean
    ) {
        val adapter = ContentRecyclerAdapter(data)
        setLayoutManagerToScreenSize()
        binding.recycler.adapter = adapter
        if (!isSearch) {
            pagination()
        }
    }

    private fun setLayoutManagerToScreenSize() {
        val display: Display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        Log.e(TAG, "setLayoutManagerToScreenSize: $dpWidth" )
        val columns = (dpWidth / 300).roundToInt()
        Log.e(TAG, "setLayoutManagerToScreenSize: $columns" )
        binding.recycler.layoutManager = if(columns > 2) {
            GridLayoutManager(this, 5)
        }else{
            GridLayoutManager(this, 3)
        }

    }

    private fun pagination() {
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.page++
                    Log.e(TAG, "onScrollStateChanged: ${viewModel.page}" )
                    viewModel.initPagedContentList(viewModel.page)
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_search, menu)
        searchHandling(menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun searchHandling(menu: Menu) {
        val menuItem = menu.findItem(R.id.search_in_toolbar)
        val searchViewItem: SearchView? = menuItem.actionView as SearchView?
        searchViewItem?.apply {
            maxWidth = Integer.MAX_VALUE
            queryHint = "Search movie"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (it.length >= 3) {
                            val list = data.page?.`content-items`?.content?.filter {
                                it!!.name!!.lowercase().contains(newText.lowercase())
                            }
                            initRecycler(
                                list as ArrayList<ContentModel.Page.ContentItems.Content?>?,
                                true
                            )
                        }

                        if (it.isEmpty()) {
                            viewModel.page = 0
                            initRecycler(data.page?.`content-items`?.content, false)
                        }
                    }

                    return false
                }

            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }


}