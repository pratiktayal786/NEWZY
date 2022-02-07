package com.example.newzy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){
    lateinit var recyclerView: RecyclerView
    lateinit var searching: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
         searching = findViewById(R.id.searching)
        val searchedNameET = findViewById<EditText>(R.id.ETSearchedName)
        val searchBTN = findViewById<Button>(R.id.BTNSearch)
        searchBTN.setOnClickListener{
            val searchedCategory = searchedNameET.editableText.toString()
            getNews(searchedCategory)
            searching.visibility = View.VISIBLE
        }

    }

    private fun getNews(searchedCategory: String) {
        val news: Call<News> = NewsServices.instanceRTF.getNews(searchedCategory)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if(news!= null)
                {
                    val adapter = NewsAdapter(this@MainActivity, news.articles)
                    recyclerView.adapter = adapter
                    searching.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("tayal", "EEEEEEEEEEEEEERRRRRRRRRRRRRROOOOOOOOOOOOOORRRRRRRRRRRRRRRR!!!!!!!!!!!!!!")
            }
        })
    }
    /*override fun onItemClicked(item: String) {
        Toast.makeText(this, "Item $item is clicked", Toast.LENGTH_SHORT).show()
    }*/
}