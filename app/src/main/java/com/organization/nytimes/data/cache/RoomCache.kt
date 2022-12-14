package com.organization.nytimes.data.cache

import com.organization.nytimes.data.cache.daos.ArticlesDao
import com.organization.nytimes.data.cache.model.CachedArticle
import com.organization.nytimes.data.cache.model.CachedArticleWithImages
import com.organization.nytimes.data.cache.model.CachedImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val articlesDao: ArticlesDao,
) : Cache {
    override fun getArticles(): Flow<List<CachedArticleWithImages>> = articlesDao.getAllArticles()

    override fun getArticle(id: Long): Flow<CachedArticleWithImages> = articlesDao.getArticleById(id)

    override suspend fun storeArticles(vararg articles: CachedArticle) =
        articlesDao.insertArticle(*articles)

    override suspend fun storeImage(vararg images: CachedImage) =
        articlesDao.insertImage(*images)
}