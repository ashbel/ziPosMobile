package com.mopanesystems.zipos.di.inventory

import com.mopanesystems.zipos.data.repository.inventory.ProductRepositoryImpl
import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository
}