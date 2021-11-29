package com.example.tejuprojectdiagnal.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.databinding.ActivityMainBinding
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var jsonHelperClass: JsonHelperClass

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val response = jsonHelperClass.convertStringToObject(R.raw.content_listing_page_1)

        Log.e(TAG, "onCreate: $response" )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.search) {
            Log.e(TAG, "onOptionsItemSelected: called" )
            val searchViewItem: SearchView? = item.actionView as SearchView?

            searchViewItem?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    //searchViewItem.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.e(TAG, "onQueryTextChange: $newText" )
                    return true
                }

            })
        }

        return super.onOptionsItemSelected(item)
    }
}