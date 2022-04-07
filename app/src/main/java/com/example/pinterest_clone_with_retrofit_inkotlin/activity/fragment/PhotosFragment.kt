package com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.MainActivity
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.adapter.PhotosAdapter
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Photo
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.network.RetrofitHttp
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosFragment private constructor() : Fragment() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var fragment: PhotosFragment? = null

        fun newInstance(): PhotosFragment? {
            if (fragment == null) {
                fragment = PhotosFragment()
            }
            return fragment
        }
    }

    private lateinit var recyclerPhotos: RecyclerView
    private lateinit var adapter: PhotosAdapter
    private lateinit var fm_loading: FrameLayout

    private var loadingCounter = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = PhotosAdapter(requireContext())
        getPhotos(loadingCounter)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_photos, container, false)
        initViews(view)
        (requireContext() as MainActivity).showBottomNavigation()

        return view
    }

    private fun initViews(view: View) {
        fm_loading = view.findViewById(R.id.fm_loading)
        recyclerPhotos = view.findViewById(R.id.rv_photos)
        recyclerPhotos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerPhotos.adapter = adapter

        recyclerPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerPhotos.canScrollVertically(1))
                    getPhotos(++loadingCounter)
            }

        })
        if(adapter.items.isNotEmpty()){
            fm_loading.visibility = View.INVISIBLE
        }
    }

    private fun getPhotos(page: Int) {
        RetrofitHttp.photosService.listPhotos(page = page)
            .enqueue(object : Callback<ArrayList<Photo>> {
                override fun onResponse(
                    call: Call<ArrayList<Photo>>,
                    response: Response<ArrayList<Photo>>,
                ) {
                    fm_loading.visibility = View.INVISIBLE
                    adapter.setItems(response.body()!!)

                    Logger.d("@@@", "PhotosList -> ${response.body()}")
                }

                override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
                    Logger.d("@@@", "PhotosList -> ${t.localizedMessage}")
                }

            })
    }

}
