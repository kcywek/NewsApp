package com.test.newsapp.data.repository.mapper.news

import com.test.newsapp.data.network.rest.model.request.NewsSortTypeRequest
import com.test.newsapp.domain.common.model.NewsSortTypeEntity
import com.test.newsapp.domain.common.model.NewsSortTypeEntity.*

internal class NewsSortTypeMapperImpl : NewsSortTypeMapper {

    override fun from(sortTypeRequest: NewsSortTypeEntity): NewsSortTypeRequest =
        when (sortTypeRequest) {
            RELEVANCY -> NewsSortTypeRequest.RELEVANCY
            POPULARITY -> NewsSortTypeRequest.POPULARITY
            PUBLISHED_AT -> NewsSortTypeRequest.PUBLISHED_AT
        }
}
