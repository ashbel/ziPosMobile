package com.mopanesystems.zipos.data.database

import android.content.Context
import androidx.room.Room
import com.mopanesystems.zipos.data.local.inventory.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java,
            "pos_db"
        ).build()
    }

    @Provides
    fun provideProductDao(db: ProductDatabase): ProductDao {
        return db.productDao()
    }
}
