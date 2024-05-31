package com.example.modulempat.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File


@Entity
data class kontenDatabase(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "deskripsi_konten")
    val deskripsi: String,

    @ColumnInfo(name = "gambar_konten")
    val image: File,

    @ColumnInfo(name = "like")
    var like: Int = 0,

    @ColumnInfo(name = "komen")
    var komen: Int = 0

) : Parcelable{
    // Konstruktor sekunder untuk membuat objek PlayerDatabase dari Parcel
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        File(parcel.readString()!!),
        parcel.readInt()
    )

    // Fungsi untuk menulis data objek PlayerDatabase ke Parcel
    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(id)
        p0.writeString(deskripsi)
        p0.writeString(image.path)
        p0.writeInt(like)
        p0.writeInt(komen)
    }

    // Fungsi untuk mendeskripsikan jenis konten khusus yang ditangani oleh Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Objek pendamping untuk PlayerDatabase yang berisi fungsi untuk membuat objek PlayerDatabase dari Parcel dan Array
    companion object CREATOR : Parcelable.Creator<kontenDatabase>{
        // Fungsi untuk membuat objek PlayerDatabase dari Parcel
        override fun createFromParcel(p0: Parcel): kontenDatabase {
            return kontenDatabase(p0)
        }

        // Fungsi untuk membuat array dari objek PlayerDatabase
        override fun newArray(size: Int): Array<kontenDatabase?> {
            return arrayOfNulls(size)
        }
    }
}
