package com.assignment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.pojo.ListResponse
import com.assignment.pojo.Row
import com.assignment.adapter.RecyclerViewAdapter
import com.assignment.di.components.DaggerMainActivityComponent
import com.assignment.di.components.MainActivityComponent
import com.assignment.di.modules.MainActivityContextModule
import com.assignment.di.qualifiers.ActivityContext
import com.assignment.di.qualifiers.ApplicationContext
import com.assignment.retrofit.APIInterface
import com.assignment.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvNoInternet: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mainActivityComponent: MainActivityComponent

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

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()

        //getting the toolbar
        toolbar = findViewById(R.id.toolbar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
        tvNoInternet = findViewById(R.id.tv_no_internet)

        setToolbarTitle("")

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val applicationComponent = MyApplication.get(this).getApplicationComponen()
        mainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityContextModule(MainActivityContextModule(this))
            .applicationComponent(applicationComponent)
            .build()

        mainActivityComponent.injectMainActivity(this)
        recyclerView.adapter = recyclerViewAdapter

        // Set an on refresh listener for swipe refresh layout
        swipeRefreshLayout.setOnRefreshListener {
            refreshContent()
        }
        if (Util.isNetworkConnected(mContext)) {
            setUIVisibility(View.VISIBLE, View.INVISIBLE, "")
            getListData()
        } else {
            setUIVisibility(View.INVISIBLE, View.VISIBLE, "No Internet Found")
        }
    }

    private fun setUIVisibility(recycler: Int, tv: Int, message : String) {
        recyclerView.visibility = recycler
        tvNoInternet.visibility = tv
        tvNoInternet.text = message
    }

    private fun getListData() {
        apiInterface.getRows().enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                setToolbarTitle(response.body()!!.title)
                populateRecyclerView(response.body()!!.rows)
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                setToolbarTitle("")
                setUIVisibility(View.INVISIBLE, View.VISIBLE, call.toString())
            }
        })
    }

    private fun refreshContent() {
        // Initialize a new Runnable
        mRunnable = Runnable {
            // Updated list data
            if (Util.isNetworkConnected(mContext)) {
                setUIVisibility(View.VISIBLE, View.INVISIBLE, "")
                getListData()
            } else {
                setToolbarTitle("")
                setUIVisibility(View.INVISIBLE, View.VISIBLE, "No Internet Found")
            }

            // Hide swipe to refresh icon animation
            swipeRefreshLayout.isRefreshing = false
        }

        // Execute the task after specified time
        mHandler.postDelayed(
            mRunnable,
            5 // Delay 1 to 5 seconds
        )
    }

    private fun populateRecyclerView(response: List<Row>) {
        recyclerViewAdapter.getData()?.clear()
        if(response.isNotEmpty()) {
            recyclerViewAdapter.setData(response)
        }else{
            setUIVisibility(View.INVISIBLE, View.VISIBLE, "No Data Found")
        }
    }

    override fun launchIntent(filmName: String) {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show()
    }

    private fun setToolbarTitle(title: String) {
        //setting the title
        toolbar.title = title
        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar)
    }
}

