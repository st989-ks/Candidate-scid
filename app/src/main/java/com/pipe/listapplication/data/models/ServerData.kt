package com.pipe.listapplication.data.models

data class ServerData(
    val error: Any,
    val result: Result,
    val success: Boolean
)

data class Result(
    val current_page: Int,
    val data: List<Data>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class Data(
    val date: String,
    val description: String,
    val id: Int,
    val name: String
)

data class Link(
    val active: Boolean,
    val label: String,
    val url: String
)