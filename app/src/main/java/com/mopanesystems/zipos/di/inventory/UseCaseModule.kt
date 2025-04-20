package com.mopanesystems.zipos.di.inventory

import com.mopanesystems.zipos.domain.repository.inventory.ProductRepository
import com.mopanesystems.zipos.domain.usecase.inventory.AddProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.AddProductsUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.DeleteProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.GetAllProductsUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.GetByIdProductUseCase
import com.mopanesystems.zipos.domain.usecase.inventory.UpdateProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllProductsUseCase(
        repository: ProductRepository
    ): GetAllProductsUseCase = GetAllProductsUseCase(repository)

    @Provides
    fun provideAddProductUseCase(repository: ProductRepository): AddProductUseCase =
        AddProductUseCase(repository)

    @Provides
    fun provideAddProductsUseCase(repository: ProductRepository): AddProductsUseCase =
        AddProductsUseCase(repository)

    @Provides
    fun provideUpdateProductUseCase(repository: ProductRepository): UpdateProductUseCase =
        UpdateProductUseCase(repository)

    @Provides
    fun provideGetByIdProductUseCase(repository: ProductRepository): GetByIdProductUseCase =
        GetByIdProductUseCase(repository)

    @Provides
    fun provideDeleteProductUseCase(repository: ProductRepository): DeleteProductUseCase =
        DeleteProductUseCase(repository)
}
