package ru.iw.invsetwalet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.iw.invsetwalet.db.dao.AccountDao
import ru.iw.invsetwalet.db.entity.AccountEntity

@Database(entities = [AccountEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract val accountDao: AccountDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context, AppDb::class.java, "app.db"
        )

            //don't do that!!! look down
            .allowMainThreadQueries()
            .build()
    }
}