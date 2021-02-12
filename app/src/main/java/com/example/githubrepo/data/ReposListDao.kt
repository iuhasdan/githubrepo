package com.example.githubrepo.data

data class ReposList(val items: ArrayList<Items>)
data class Items(
    val name: String,
    val owner: Owner,
    val forks: Int,
    val watchers_count: Long,
    val stargazers_count: Int
)

data class Owner(val url: String, val login: String)
data class Readme(val content: String)
