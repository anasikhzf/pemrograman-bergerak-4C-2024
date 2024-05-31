package com.example.modulempat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// Mendeklarasikan database dengan entitas PlayerDatabase dan versi 1
@Database(entities = [kontenDatabase::class], version = 1)

// Menggunakan konverter tipe khusus untuk mengubah tipe data File menjadi String dan sebaliknya
@TypeConverters(konverter::class)

// Membuat kelas abstrak AppDatabase yang merupakan turunan dari RoomDatabase
abstract class dataBase : RoomDatabase() {

    // Mendeklarasikan fungsi abstrak yang mengembalikan KontenDao
    abstract fun kontenDao() : kontenDao

    // Membuat objek pendamping untuk database
    companion object{
        // Mendeklarasikan variabel INSTANCE yang akan menyimpan instance dari database
        @Volatile
        private var INSTANCE: dataBase? = null

        // Membuat fungsi statis untuk mendapatkan instance database
        @JvmStatic
        fun getDatabase(context: Context): dataBase{
            // Jika INSTANCE null, maka akan dibuat instance baru
            if(INSTANCE == null){
                // Menggunakan synchronized untuk mencegah akses bersamaan dari beberapa thread
                synchronized(dataBase::class.java){
                    // Membuat instance baru dari AppDatabase
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        dataBase::class.java, "app_database"
                    )
                        .build()
                }
            }
            // Mengembalikan instance dari AppDatabase
            return INSTANCE as dataBase
        }
    }
}