package com.example.githubrepo.ui

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepo.R
import com.example.githubrepo.adapter.RecyclerViewAdapter
import com.example.githubrepo.data.Items
import com.example.githubrepo.utilities.DecodeText
import com.example.githubrepo.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_repo_info.view.*
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

/**
 * A simple [Fragment] subclass.
 * Use the [RepoInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoInfoFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_info, container, false)
        val args = arguments

        val user = args?.getString("user")
        val login = args?.getString("login")
        val name = args?.getString("name")
        val forks = args?.getString("forks")
        val watchers = args?.getString("watchers")
        val url = args?.getString("url")

        initViewModel(login, name)

        val readme = args?.getString("read_me")

        view.user_tv.text = user
        view.repo_name_tv.text = name
        view.forks_tv.text = forks
        view.watchers_tv.text = watchers
        view.url_tv.text = url
        view.read_me_tv.text = readme
        return view
    }

    private fun initViewModel(login: String?, name: String?) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        if (login != null && name != null) {
            viewModel.setContentValues(login, name)
        }

        viewModel.getContentObserver().observe(this, {
            if (it != null) {
                val decoder = DecodeText()
                view?.read_me_tv?.text = decoder.decodeString((it.content))
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.error_retrieving_data),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.callApi()
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(items: ArrayList<Items>, position: Int): RepoInfoFragment {
            RepoInfoFragment()
            val args = Bundle()
            args.putString("user", items[position].owner.login)
            args.putString("name", items[position].name)
            args.putString("forks", items[position].forks.toString())
            args.putString("watchers", items[position].watchers_count.toString())
            args.putString("url", items[position].owner.url)
            args.putString("login", items[position].owner.login)

            val fragment = RepoInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onItemClick(items: ArrayList<Items>, position: Int) {
        TODO("Not yet implemented")
    }
}
