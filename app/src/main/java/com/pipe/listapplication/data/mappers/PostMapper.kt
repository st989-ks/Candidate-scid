package com.pipe.listapplication.data.mappers

import com.pipe.listapplication.data.models.Data
import com.pipe.listapplication.domain.entity.PostEntity


internal fun Data.toPostEntity() = PostEntity(id, name, description, date)

internal fun List<Data>.toPostEntity(): List<PostEntity> = this.map { it.toPostEntity() }