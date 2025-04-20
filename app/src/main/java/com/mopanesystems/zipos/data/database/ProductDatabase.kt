package com.mopanesystems.zipos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mopanesystems.zipos.data.local.inventory.ProductDao
import com.mopanesystems.zipos.data.local.inventory.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

//    companion object {
//        @Volatile private var INSTANCE: ProductDatabase? = null
//
//        fun getInstance(context: Context): ProductDatabase {
//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(
//                    context.applicationContext,
//                    ProductDatabase::class.java,
//                    "pos_db"
//                ).build().also { INSTANCE = it }
//            }
//        }
//    }
}
