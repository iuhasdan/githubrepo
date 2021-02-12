package com.example.githubrepo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepo.R
import com.example.githubrepo.adapter.RecyclerViewAdapter
import com.example.githubrepo.data.Items
import com.example.githubrepo.data.ReposList
import com.example.githubrepo.viewmodel.MainActivityViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RepoDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoDetailsFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_details, container, false)
        initViewModel(view)
        initViewModel()

        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getReposListObserver().observe(this, {
            if (it != null) {
                recyclerAdapter.setData(it.items)
            } else {
                Toast.makeText(activity, getString(R.string.error_retrieving_data), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiCall()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RepoDetailsFragment()
    }

    override fun onItemClick(items: ArrayList<Items>, position: Int) {

        val fragment = RepoInfoFragment.newInstance(items, position)

        fragmentManager
            ?.beginTransaction()
            ?.replace(android.R.id.content, fragment)
            ?.addToBackStack(null)
            ?.commit()

    }
}