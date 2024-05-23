package com.example.modulempat.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

//kelas ini adalah entitas Room yang akan direpresentasikan sebagai tabel di database
@Entity
data class kontenDatabase(     // mendefinisikan kelas data kontenDatabse
    @PrimaryKey(autoGenerate = true)  //anotasi yang menandakan kolom id mrp kunci utama dan dihasilkan scr otomatis

    // anotasi yang digunakan untuk menentukan nama kolom didatabse
    @ColumnInfo(name = "id")
    val id: Int = 0, //kolom kunci utama

    @ColumnInfo(name = "deskripsi_konten")
    val deskripsi: String, // kolom yg menyimpan deskripsi konten

    @ColumnInfo(name = "gambar_konten")
    val image: File,

    @ColumnInfo(name = "like")
    var like: Int = 0,

    @ColumnInfo(name = "komen")
    var komen: Int = 0

//    Konstruktor yang digunakan untuk membuat instance kontenDatabase dari objek Parcel
) : Parcelable{
    constructor(parcel: Parcel) : this(

        parcel.readInt(),    //digunakan untuk membaca nilai id dr parcel
        parcel.readString()!!,
        File(parcel.readString()!!),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {  //Menulis objek kontenDatabase ke dalam Parcel

        p0.writeInt(id)    //menulis id ke dalam parcel
        p0.writeString(deskripsi)
        p0.writeString(image.path)
        p0.writeInt(like)
        p0.writeInt(komen)
    }

    // mengembalikan 0 dan digunakan untuk mendeskripsikan jenis konten khusus dari Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Objek pendamping yang mengimplementasikan Parcelable.Creator
    companion object CREATOR : Parcelable.Creator<kontenDatabase>{

        // metode yg digunakan untuk membuat instance kontenDatabase dr objek parcel
        override fun createFromParcel(p0: Parcel): kontenDatabase {
            return kontenDatabase(p0)
        }

        //metode untuk membuat array kontenDatabase dengan ukuran tertentu
        override fun newArray(size: Int): Array<kontenDatabase?> {
            return arrayOfNulls(size)
        }
    }
}
