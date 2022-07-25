package com.pipe.listapplication.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostEntity(
    val id: Int,
    val name: String,
    val description: String,
    val date: String
): Parcelable
