package com.example.postapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var postAdapter: PostAppAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeRecyclerView()

        fetchPosts()
    }

    fun fetchPosts() {
        val apiClient = ApiClient.buildApiInterface(PostApiInterface::class.java)
        val request = apiClient.fetchPosts()
        request.enqueue(object : retrofit2.Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    posts?.let {
                        displayPosts(it)
                        Toast.makeText(
                            baseContext,
                            "fetched ${posts!!.size} posts",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }


            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
                Toast.makeText(baseContext, p1.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    fun initializeRecyclerView() {
        binding.tvinform.layoutManager = LinearLayoutManager(this)
    }

    fun displayPosts(post: List<Post>) {
        postAdapter = PostAppAdapter(post, this)
        binding.tvinform.adapter = postAdapter
    }
}






