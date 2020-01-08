package com.assignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.pojo.ListResponse
import com.assignment.pojo.Row
import com.assignment.adapter.RecyclerViewAdapter
import com.assignment.di.components.DaggerMainActivityComponent
import com.assignment.di.components.MainActivityComponent
import com.assignment.di.modules.MainActivityContextModule
import com.assignment.di.qualifiers.ActivityContext
import com.assignment.di.qualifiers.ApplicationContext
import com.assignment.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private var recyclerView: RecyclerView? = null
    var mainActivityComponent: MainActivityComponent? = null

    @Inject
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    @Inject
    lateinit var apiInterface: APIInterface

    @Inject
    @ApplicationContext
    lateinit var mContext: Context

    @Inject
    @ActivityContext
    lateinit var activityContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.setLayoutManager(LinearLayoutManager(this@MainActivity))

        val applicationComponent = MyApplication.get(this).getApplicationComponen()
        mainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityContextModule(MainActivityContextModule(this))
            .applicationComponent(applicationComponent)
            .build()

        mainActivityComponent?.injectMainActivity(this)
        recyclerView?.setAdapter(recyclerViewAdapter)

        apiInterface.getPeople().enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                populateRecyclerView(response.body()!!.rows)
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.d("Error:", call.toString())
            }
        })
    }

    private fun populateRecyclerView(response: List<Row>) {
        recyclerViewAdapter.setData(response)
    }

    override fun launchIntent(filmName: String) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show()
//        startActivity(Intent(activityContext, DetailActivity::class.java).putExtra("url", url))
    }
}

