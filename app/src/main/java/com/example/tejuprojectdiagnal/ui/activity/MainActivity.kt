package com.example.tejuprojectdiagnal.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.databinding.ActivityMainBinding
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import com.example.tejuprojectdiagnal.mvvm.view_model.ContentViewModel
import com.example.tejuprojectdiagnal.ui.adapter.ContentRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ContentViewModel by viewModels()

    @Inject
    lateinit var jsonHelperClass: JsonHelperClass

    var page = 0
    private lateinit var data: ContentModel

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        data = jsonHelperClass.convertStringToObject(R.raw.content_listing_page_1)
        initRecycler(data.page?.`content-items`?.content)
    }

    private fun initRecycler(data: ArrayList<ContentModel.Page.ContentItems.Content?>?) {

        val adapter = ContentRecyclerAdapter(data)

        binding.recycler.layoutManager = GridLayoutManager(this@MainActivity, 3, GridLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter

        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    if(page == 0) {
                        Log.e(TAG, "onScrollStateChanged: end", )
                        val data2 = jsonHelperClass.convertStringToObject(R.raw.content_listing_page_2)
                        data2.page?.`content-items`?.content?.let {
                            data?.apply {
                                addAll(it)
                                initRecycler(data)
                                page++
                            }
                        }
                    } else if(page == 1) {
                        Log.e(TAG, "onScrollStateChanged: end", )
                        val data2 = jsonHelperClass.convertStringToObject(R.raw.content_listing_page_3)
                        data2.page?.`content-items`?.content?.let {
                            data?.apply {
                                addAll(it)
                                initRecycler(data)
                                page++
                            }
                        }
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_search,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.search_in_toolbar) {
            Log.e(TAG, "onOptionsItemSelected: called" )
            val searchViewItem: SearchView? = item.actionView as SearchView?

            searchViewItem?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //searchViewItem.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.e(TAG, "onQueryTextChange: called" )
                    newText?.let {
                        Log.e(TAG, "onQueryTextChange: called" )
                        if(it.length >= 3){
                            val list = data.page?.`content-items`?.content?.filter { newText == it!!.name }
                            Log.e(TAG, "onQueryTextChange: list: $list" )
                            initRecycler(list as ArrayList<ContentModel.Page.ContentItems.Content?>?)
                        }
                    }

                    return false
                }

            })
        }

        return super.onOptionsItemSelected(item)
    }
}