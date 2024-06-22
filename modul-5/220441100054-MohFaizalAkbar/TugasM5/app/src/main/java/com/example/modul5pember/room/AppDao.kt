package com.example.modul5pember.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Interface AppDao adalah Data Access Object (DAO) yang berfungsi sebagai jembatan antara aplikasi dan database SQLite.
 * DAO berisi metode yang menyediakan akses ke operasi database seperti insert dan query.
 *
 * Anotasi @Dao digunakan untuk memberi tahu Room bahwa interface ini adalah DAO.
 */

@Dao
interface AppDao {

    /**
     * Fungsi insertPost digunakan untuk memasukkan data pemain ke dalam database.
     * Anotasi @Insert digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk memasukkan data.
     * Parameter onConflict digunakan untuk menentukan apa yang harus dilakukan Room jika data yang dimasukkan memiliki konflik dengan data yang sudah ada di database.
     * OnConflictStrategy.IGNORE berarti jika ada konflik, Room akan mengabaikan operasi insert.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(post: PostDatabase)

    /**
     * Fungsi getAllPost digunakan untuk mendapatkan semua data pemain dari database.
     * Anotasi @Query digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk menjalankan query SQL.
     * Query "SELECT * from postdatabase ORDER BY post_name ASC" berarti memilih semua data dari tabel postdatabase dan mengurutkannya berdasarkan post_name dalam urutan ascending.
     * Fungsi ini mengembalikan LiveData yang berisi daftar semua pemain. LiveData adalah kelas dari Android Architecture Components yang memungkinkan kita untuk mengamati perubahan data dalam database dan secara otomatis memperbarui UI jika ada perubahan.
     */
    @Query("SELECT * from postdatabase ORDER BY id DESC")
    fun getAllPost(): LiveData<List<PostDatabase>>

    /**
     * Fungsi deletePost digunakan untuk menghapus data pemain dari database.
     * Anotasi @Delete digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk menghapus data.
     */
    @Delete
    fun deletePost(postDatabase: PostDatabase)

    /**
     * Fungsi updatePost digunakan untuk memperbarui data pemain dalam database.
     * Anotasi @Update digunakan untuk memberi tahu Room bahwa fungsi ini digunakan untuk memperbarui data.
     */
    @Update
    fun updatePost(postDatabase: PostDatabase)
}