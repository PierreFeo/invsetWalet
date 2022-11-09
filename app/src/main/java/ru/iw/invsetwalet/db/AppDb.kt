package ru.iw.invsetwalet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.iw.invsetwalet.db.dao.AccountDao
import ru.iw.invsetwalet.db.dao.TransactionsDao
import ru.iw.invsetwalet.db.entity.AccountEntity
import ru.iw.invsetwalet.db.entity.TransactionsEntity

@Database(
    version = 1,
    entities = [
        AccountEntity::class,
        TransactionsEntity::class
    ]
)
abstract class AppDb : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val transactionDao: TransactionsDao

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