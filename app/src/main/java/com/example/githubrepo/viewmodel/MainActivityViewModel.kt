package com.example.githubrepo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.Readme
import com.example.githubrepo.data.ReposList
import com.example.githubrepo.network.APIProvider
import com.example.githubrepo.network.GitAPI
import com.example.githubrepo.utilities.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel : ViewModel() {
    private lateinit var name: String
    private lateinit var login: String
    private var reposListLiveData: MutableLiveData<ReposList> = MutableLiveData()
    private var contentLiveData: MutableLiveData<Readme> = MutableLiveData()

    fun getReposListObserver(): MutableLiveData<ReposList> {
        return reposListLiveData
    }

    fun getContentObserver(): MutableLiveData<Readme> {
        return contentLiveData
    }

    fun setContentValues(login: String, name: String) {
        this.login = login
        this.name = name
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiProvider = APIProvider.buildRetrofitClient().create(GitAPI::class.java)
                val response = apiProvider.getDataFromApi(Constants.ANDROID, Constants.STARS, Constants.PAGE, Constants.PAGES)
                reposListLiveData.postValue(response)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun callApi() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiProvider = APIProvider.buildRetrofitClient().create(GitAPI::class.java)
                val response = apiProvider.getReadMeFromApi(login, name)
                contentLiveData.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}