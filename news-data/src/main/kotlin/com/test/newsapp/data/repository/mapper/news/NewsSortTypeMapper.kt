package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.request.NewsSortTypeRequest
import com.test.newsapp.domain.common.model.NewsSortTypeEntity

internal interface NewsSortTypeMapper {

    fun from(sortTypeRequest: NewsSortTypeEntity): NewsSortTypeRequest
}
