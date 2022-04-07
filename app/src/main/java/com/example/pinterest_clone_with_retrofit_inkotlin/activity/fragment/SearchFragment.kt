package com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.MainActivity
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.adapter.PhotosAdapter
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.adapter.SearchTitleAdapter
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Collection
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.MyCollection
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Search
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Topic
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.network.RetrofitHttp
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SearchFragment private constructor() : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: SearchFragment? = null

        fun newInstance(): SearchFragment? {
            if (fragment == null) {
                fragment = SearchFragment()
            }
            return fragment
        }
    }

    private var recyclerSearch: RecyclerView? = null
    private lateinit var recyclerTopic: RecyclerView
    private lateinit var adapter: PhotosAdapter
    private lateinit var adapterTitle: SearchTitleAdapter
    private lateinit var edt_search: EditText
    private lateinit var ll_search_titles: LinearLayout

    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = PhotosAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initViews(view)

        (requireContext() as MainActivity).showBottomNavigation()
        return view
    }

    private fun initViews(view: View) {
        recyclerSearch = view.findViewById(R.id.rv_search)
        recyclerSearch!!.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerSearch!!.adapter = adapter

        recyclerSearch!!.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerSearch!!.canScrollVertically(1))
                    searchPhoto(edt_search.text.toString(), ++page)
            }
        })

        recyclerTopic = view.findViewById(R.id.rv_search_titles)
        recyclerTopic.layoutManager = GridLayoutManager(requireContext(), 2)
        getMyCollects()
//        getCollections()

        ll_search_titles = view.findViewById(R.id.ll_search_titles)
        edt_search = view.findViewById(R.id.edt_search)
        edt_search.text.clear()
        edt_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && edt_search.text.isNotEmpty()) {
                page = 1
                searchPhoto(edt_search.text.toString(), page)

                recyclerSearch!!.visibility = View.VISIBLE
                ll_search_titles.visibility = View.GONE
            }
            false
        })

        if (adapter.items.isNotEmpty()) {
            recyclerSearch!!.visibility = View.VISIBLE
            ll_search_titles.visibility = View.GONE
        }
    }

    fun onBackPressed(): Boolean {
        if (ll_search_titles.visibility == View.GONE) {
            recyclerSearch!!.visibility = View.GONE
            ll_search_titles.visibility = View.VISIBLE
            edt_search.text.clear()
            return false
        }
        return true
    }

    private fun refreshAdapterTopics(items: ArrayList<MyCollection>) {
        adapterTitle = SearchTitleAdapter(requireContext(), items) {
            searchPhoto(it, 1)
            recyclerSearch!!.visibility = View.VISIBLE
            ll_search_titles.visibility = View.GONE
            edt_search.text.clear()
            edt_search.text.insert(0, it)
        }
        recyclerTopic.adapter = adapterTitle
    }

    private fun getMyCollects() {
        val collects = ArrayList<MyCollection>()

        collects.add(MyCollection("PIZZA",
            "https://images.unsplash.com/photo-1601924582970-9238bcb495d9?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
        collects.add(MyCollection("Ice cream",
            "https://images.unsplash.com/photo-1560008581-09826d1de69e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
        collects.add(MyCollection("Coffee Culture ☕ ",
            "https://images.unsplash.com/photo-1567016530961-54fd42f2d51f?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
        collects.add(MyCollection("health",
            "https://images.unsplash.com/photo-1553531889-65d9c41c2609?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
        collects.add(MyCollection("New Year",
            "https://images.unsplash.com/photo-1514446945-952d86c3449b?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))
        collects.add(MyCollection("Work from Anywhere",
            "https://images.unsplash.com/photo-1584677626646-7c8f83690304?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max"))

        refreshAdapterTopics(collects)
    }

    private fun searchPhoto(query: String, page: Int) {
        RetrofitHttp.photosService.searchPhoto(query).enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                if (page == 1)
                    adapter.items.clear()
                adapter.items.addAll(response.body()!!.results!!)
                adapter.notifyDataSetChanged()

                Logger.d("@@@", "Search -> ${response.body()}")
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Logger.d("@@@", "Search -> ${t.localizedMessage}")
            }

        })
    }

    private fun getCollections() {
        RetrofitHttp.photosService.getCollections()
            .enqueue(object : Callback<ArrayList<Collection>> {
                override fun onResponse(
                    call: Call<ArrayList<Collection>>,
                    response: Response<ArrayList<Collection>>,
                ) {
                    if (response.isSuccessful) {
//                    refreshAdapterTopics(response.body()!!)
                    }
                    Logger.d("@@@", "Collection -> ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<Collection>>, t: Throwable) {
                    Logger.d("@@@", "Collection -> ${t.localizedMessage}")
                }

            })
    }

    private fun getTopics() {
        RetrofitHttp.photosService.getTopics().enqueue(object : Callback<ArrayList<Topic>> {
            override fun onResponse(
                call: Call<ArrayList<Topic>>,
                response: Response<ArrayList<Topic>>,
            ) {
                if (response.isSuccessful) {
                    //refreshAdapterTopics(response.body()!!)
                }
                Logger.d("@@@", "Topics -> ${response.body()}")
            }

            override fun onFailure(call: Call<ArrayList<Topic>>, t: Throwable) {
                Logger.d("@@@", "Topics -> ${t.localizedMessage}")
            }

        })
    }


}