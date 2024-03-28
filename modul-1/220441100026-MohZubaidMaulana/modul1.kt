fun main() {
    println("||=========================||")
    println("||-------SUGENG RABU-------||")
    println("||=========================||")
    println("")
    pembeli()
    println("")
    daftar()
    println("")
    tanda()
    beli()
    tanda()
    println("")
    println("-----------SEKALANGKONG-----------")
} 

//function

fun pembeli(){
    val orang = Orang()
    println(orang.identitas())
}
class Orang() {
    fun identitas() = "nama pembeli = Bimo"
}
fun daftar(){
    println("DAFTAR BARANG KELONTONG:")
    println("")
    println("1. Bedak My Baby = RP.24.000 ")
    println("2. Buku Gambar = RP.25.000 ")
    println("3. Tolak Angin = RP.35.000 ")
    println("4. Minyak Kayu Putih = RP.75.000 ")
    println("4. Minyak Kemiri = RP.66.000 ")
}
fun tanda (){
    var tandai = 3
    while (tandai < 5){
    	tandai++
        println("=====================================")
    }
}
fun beli (){
    var jumlah = 5
    if (jumlah == 10){
        println("anda membeli HP : ")
        println("1. Bedak My Baby = RP.24.000 ")
   		println("2. Buku Gambar = RP.25.000 ")
    	println("3. Tolak Angin = RP.35.000 ")
    } else if (jumlah > 12 || jumlah < 15){
        println("anda membeli barang : ")
        println("Minyak Kemiri dengan harga RP 66.000")
    }
    println("")
    println("List barang yang dibeli:")
    val barangYangDibeli = listOf("Bedak My Baby", "Buku Gambar", "Tolak Angin", "Minyak Kayu Putih", "Minyak Kemiri")
    for (i in 1..jumlah) {
        println("$i. ${barangYangDibeli[i % barangYangDibeli.size]}")
    }
    	
}