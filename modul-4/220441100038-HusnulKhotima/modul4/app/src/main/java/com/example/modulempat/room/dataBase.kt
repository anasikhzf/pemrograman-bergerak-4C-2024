package com.example.modulempat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Mendeklarasikan database dengan entitas kontenDatabase dan versi 2
@Database(entities = [kontenDatabase::class], version = 2)
@TypeConverters(konverter::class)
abstract class dataBase : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak yang mengembalikan kontenDao
    abstract fun kontenDao(): kontenDao

    companion object {
        // Mendeklarasikan variabel instance yang akan menyimpan instance dari database
        @Volatile
        private var INSTANCE: dataBase? = null

        // Definisikan migrasi dari versi 1 ke versi 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Menambahkan kolom baru 'komen' ke tabel kontenDatabase
                database.execSQL("ALTER TABLE kontenDatabase ADD COLUMN komen INTEGER NOT NULL DEFAULT 0")
            }
        }

        // Membuat fungsi statis untuk mendapatkan instance database
        @JvmStatic
        fun getDatabase(context: Context): dataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dataBase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
