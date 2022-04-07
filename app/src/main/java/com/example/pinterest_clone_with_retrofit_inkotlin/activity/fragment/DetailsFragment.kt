package com.example.pinterest_clone_with_retrofit_inkotlin.activity.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.MainActivity
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.adapter.DetailsAdapter
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.Photo
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.utils.Logger

class DetailsFragment(var items: ArrayList<Photo>, var position: Int) : Fragment() {

//    companion object{
//        private var fragment: DetailsFragment? = null
//
//        fun newInstance(items: ArrayList<Photo>, position: Int): DetailsFragment?{
//            if (fragment == null){
//                fragment = DetailsFragment(items, position)
//            }
//            return fragment
//        }
//    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var detailsAdapter: DetailsAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onAttach(context: Context) {
        super.onAttach(context)

        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        requireContext()
        initViews(view)
        (requireContext() as MainActivity).hideBottomNavigation()
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_details)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()

        snapHelper.attachToRecyclerView(recyclerView)
        refreshAdapter(items)
        recyclerView.scrollToPosition(position)

        Logger.d("ClickedPosition", "Fragment -> $position")
    }

    private fun refreshAdapter(items: ArrayList<Photo>){
        detailsAdapter = DetailsAdapter(requireContext(), items)
        recyclerView.adapter = detailsAdapter
    }

    override fun onDetach() {
        super.onDetach()

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

}