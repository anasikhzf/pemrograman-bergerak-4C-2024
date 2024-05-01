//Sukma Ayu Maharani
//220441100077
//Pemrograman Bergerak-4C
// Layout

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="22dp"
        android:layout_marginTop="59dp"
        android:fontFamily="@font/poppins_bold"
        android:text="Universitas Trunojoyo"
        android:textColor="#000000"
        android:textSize="22sp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView2" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:fontFamily="@font/poppins_light"
        android:text="Selamat datang di Aplikasi \nmilik Universitas Trunojoyo"
        android:textColor="#000000"
        android:textSize="16sp"
        app:layout_constraintStart_toStartOf="@+id/textView"
        app:layout_constraintTop_toBottomOf="@+id/textView" />

    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:adjustViewBounds="true"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/gambar1" />

    <EditText
        android:id="@+id/editTextText2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="59dp"
        android:layout_marginEnd="22dp"
        android:ems="10"
        android:fontFamily="@font/poppins"
        android:inputType="text"
        android:hint="Masukkan nama kamu"
        android:textColor="#635B5B"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/textView2"
        app:layout_constraintTop_toBottomOf="@+id/textView2" />

    <Button
        android:id="@+id/btnLanjut"
        android:layout_width="283dp"
        android:layout_height="56dp"
        android:layout_marginTop="45dp"

        android:backgroundTint="@color/black"
        android:text="Lanjut"
        android:textSize="20sp"
        app:cornerRadius="10dp"
        app:layout_constraintEnd_toEndOf="@+id/editTextText2"
        app:layout_constraintStart_toStartOf="@+id/editTextText2"
        app:layout_constraintTop_toBottomOf="@+id/editTextText2" />


</androidx.constraintlayout.widget.ConstraintLayout>

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity2">

    <TextView
        android:id="@+id/textView3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="18dp"

        android:layout_marginTop="23dp"
        android:fontFamily="@font/poppins_bold"
        android:text="Universitas Trunojoyo"
        android:textColor="#000000"
        android:textSize="18dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textView4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="4dp"
        android:fontFamily="@font/poppins"
        android:text="Profile"
        android:textColor="#000000"
        android:textSize="15dp"
        app:layout_constraintStart_toStartOf="@+id/textView3"
        app:layout_constraintTop_toBottomOf="@+id/textView3" />

    <ImageView
        android:id="@+id/imageView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="27dp"
        android:layout_marginEnd="23dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/logoutm2" />

    <ImageView
        android:id="@+id/imageView7"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="68dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView4"
        app:srcCompat="@drawable/gambarutm" />

    <TextView
        android:id="@+id/textView5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="21dp"
        android:fontFamily="@font/poppins_bold"
        android:text="Universitas Trunojoyo"
        android:textColor="#000000"
        android:textSize="18dp"
        app:layout_constraintEnd_toEndOf="@+id/imageView7"
        app:layout_constraintStart_toStartOf="@+id/imageView7"
        app:layout_constraintTop_toBottomOf="@+id/imageView7" />

    <TextView
        android:id="@+id/textView6"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="39dp"
        android:fontFamily="@font/poppins_semibold"
        android:text="Profil Singkat"
        android:textColor="#000000"
        android:textSize="17dp"
        app:layout_constraintStart_toStartOf="@+id/textView4"
        app:layout_constraintTop_toBottomOf="@+id/textView5" />

    <TextView
        android:id="@+id/textView7"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_gravity="start|end"
        android:layout_marginTop="12dp"
        android:layout_marginEnd="19dp"
        android:fontFamily="@font/poppins"
        android:justificationMode="inter_word"
        android:text="Universitas Trunojoyo Madura atau UTM adalah perguruan tinggi negeri yang terletak di Kamal, Kabupaten Bangkalan, Jawa Timur, Pulau Madura, Indonesia. Universitas Trunojoyo Madura dahulu merupakan universitas swasta yang resmi menjadi perguruan tinggi negeri berdasarkan Keputusan Presiden tanggal 5 Juli 2001. Perguruan tinggi ini diresmikan pada tanggal 23 Juli 2001 oleh Presiden Abdurrahman Wahid. Universitas Trunojoyo Madura merupakan perguruan tinggi negeri ke-7 di Jawa Timur."
        android:textColor="#000000"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="@+id/textView6"
        app:layout_constraintTop_toBottomOf="@+id/textView6"
        tools:ignore="MissingConstraints" />

</androidx.constraintlayout.widget.ConstraintLayout>

<?xml version="1.0" encoding="utf-8"?>
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/scrollView2"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivity4">

                <TextView
                    android:id="@+id/textView8"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="18dp"
                    android:layout_marginTop="23dp"
                    android:fontFamily="@font/poppins_bold"
                    android:text="Universitas Trunojoyo"
                    android:textSize="18sp"
                    app:layout_constraintStart_toStartOf="parent"
                    app:layout_constraintTop_toTopOf="parent" />

                <TextView
                    android:id="@+id/textView9"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="4dp"
                    android:fontFamily="@font/poppins"
                    android:text="Beranda"
                    android:textColor="#000000"
                    android:textSize="15dp"
                    app:layout_constraintStart_toStartOf="@+id/textView8"
                    app:layout_constraintTop_toBottomOf="@+id/textView8" />

                <ImageView
                    android:id="@+id/imgKembali"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="27dp"
                    android:layout_marginEnd="23dp"
                    app:layout_constraintEnd_toEndOf="parent"
                    app:layout_constraintTop_toTopOf="parent"
                    app:srcCompat="@drawable/logoutm2" />

                <TextView
                    android:id="@+id/textView10"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="36dp"
                    android:fontFamily="@font/poppins_medium"

                    android:text="Aktivitas Kegiatan"
                    android:textColor="#000000"
                    android:textSize="15sp"
                    app:layout_constraintStart_toStartOf="@+id/textView9"
                    app:layout_constraintTop_toBottomOf="@+id/textView9" />

                <ImageView
                    android:id="@+id/imageView12"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="12dp"
                    app:layout_constraintStart_toStartOf="@+id/textView10"
                    app:layout_constraintTop_toBottomOf="@+id/textView10"
                    app:srcCompat="@drawable/orang1" />

                <ImageView
                    android:id="@+id/imageView13"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="27dp"
                    app:layout_constraintStart_toEndOf="@+id/imageView12"
                    app:layout_constraintTop_toTopOf="@+id/imageView12"
                    app:srcCompat="@drawable/orang2" />

                <ImageView
                    android:id="@+id/imageView15"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="27dp"
                    android:layout_marginTop="27dp"
                    app:layout_constraintStart_toEndOf="@+id/imageView3"
                    app:layout_constraintTop_toBottomOf="@+id/imageView13"
                    app:srcCompat="@drawable/orang2" />

                <TextView
                    android:id="@+id/textView11"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="38dp"
                    android:fontFamily="@font/poppins_semibold"
                    android:text="Artikel"
                    android:textSize="15sp"
                    app:layout_constraintStart_toStartOf="@+id/imageView3"
                    app:layout_constraintTop_toBottomOf="@+id/imageView3" />

                <ImageView
                    android:id="@+id/imageView3"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="27dp"
                    app:layout_constraintStart_toStartOf="@+id/imageView12"
                    app:layout_constraintTop_toBottomOf="@+id/imageView12"
                    app:srcCompat="@drawable/orang1" />

                <ImageView
                    android:id="@+id/imageView"
                    android:layout_width="116dp"
                    android:layout_height="116dp"
                    android:layout_marginTop="15dp"
                    app:layout_constraintStart_toStartOf="@+id/textView11"
                    app:layout_constraintTop_toBottomOf="@+id/textView11"
                    app:srcCompat="@drawable/orang1" />

                <TextView
                    android:id="@+id/textView18"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginStart="13dp"
                    android:layout_marginTop="11dp"
                    android:fontFamily="@font/poppins_medium"
                    android:text="Terlalu Jago, Trunojoyo Juara Lagi"
                    android:textSize="13sp"
                    app:layout_constraintStart_toEndOf="@+id/imageView"
                    app:layout_constraintTop_toTopOf="@+id/imageView" />

                <TextView
                    android:id="@+id/textView19"
                    android:layout_width="228dp"
                    android:layout_height="51dp"
                    android:layout_marginTop="15dp"
                    android:justificationMode="inter_word"
                    android:fontFamily="@font/poppins_light"
                    android:text="Universitas Trunojoyo berhasil meraih juara 1 dalam ajang perlombaan debat nasional yang diselenggarakan oleh..."
                    android:textSize="11sp"
                    app:layout_constraintStart_toStartOf="@+id/textView18"
                    app:layout_constraintTop_toBottomOf="@+id/textView18" />

                <ImageView
                    android:id="@+id/imageView6"
                    android:layout_width="116dp"
                    android:layout_height="116dp"
                    android:layout_marginTop="26dp"
                    app:layout_constraintStart_toStartOf="@+id/imageView"
                    app:layout_constraintTop_toBottomOf="@+id/imageView"
                    app:srcCompat="@drawable/orang1" />

                <TextView
                    android:id="@+id/textView20"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="11dp"
                    android:fontFamily="@font/poppins_medium"
                    android:text="Terlalu Jago, Trunojoyo Juara Lagi"
                    android:textSize="13sp"
                    app:layout_constraintStart_toStartOf="@+id/textView19"
                    app:layout_constraintTop_toTopOf="@+id/imageView6" />

                <TextView
                    android:id="@+id/textView21"
                    android:layout_width="228dp"
                    android:layout_height="51dp"
                    android:layout_marginTop="15dp"
                    android:justificationMode="inter_word"
                    android:fontFamily="@font/poppins_light"
                    android:text="Universitas Trunojoyo berhasil meraih juara 1 dalam ajang perlombaan debat nasional yang diselenggarakan oleh..."
                    android:textSize="11sp"
                    app:layout_constraintStart_toStartOf="@+id/textView20"
                    app:layout_constraintTop_toBottomOf="@+id/textView20" />

                <ImageView
                    android:id="@+id/imageView8"
                    android:layout_width="116dp"
                    android:layout_height="116dp"
                    android:layout_marginTop="26dp"
                    app:layout_constraintStart_toStartOf="@+id/imageView6"
                    app:layout_constraintTop_toBottomOf="@+id/imageView6"
                    app:srcCompat="@drawable/orang1" />

                <TextView
                    android:id="@+id/textView12"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="11dp"
                    android:fontFamily="@font/poppins_medium"
                    android:text="Terlalu Jago, Trunojoyo Juara Lagi"
                    android:textSize="13sp"
                    app:layout_constraintStart_toStartOf="@+id/textView21"
                    app:layout_constraintTop_toTopOf="@+id/imageView8" />

                <TextView
                    android:id="@+id/textView13"
                    android:layout_width="228dp"
                    android:layout_height="51dp"
                    android:layout_marginTop="15dp"
                    android:justificationMode="inter_word"
                    android:fontFamily="@font/poppins_light"
                    android:text="Universitas Trunojoyo berhasil meraih juara 1 dalam ajang perlombaan debat nasional yang diselenggarakan oleh..."
                    android:textSize="11sp"
                    app:layout_constraintStart_toStartOf="@+id/textView12"
                    app:layout_constraintTop_toBottomOf="@+id/textView12" />

        </androidx.constraintlayout.widget.ConstraintLayout>

</ScrollView>

package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClick: Button = findViewById(R.id.btnLanjut)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

            if(v != null ) {
                when (v.id) {
                    R.id.btnLanjut -> {
                        val pindah = Intent(this, MainActivity4::class.java)
                        startActivity(pindah)
                    }
                }
            }
    }

}

package com.example.project2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity4 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val btnClick: ImageView = findViewById(R.id.imgKembali)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(v != null ) {
            when (v.id) {
                R.id.imgKembali -> {
                    val pindah = Intent(this, MainActivity2::class.java)
                    startActivity(pindah)
                }
            }
        }
    }

}