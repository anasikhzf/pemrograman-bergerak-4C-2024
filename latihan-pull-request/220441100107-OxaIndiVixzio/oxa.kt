// Class untuk merepresentasikan makanan
class Food(val name: String, val price: Int)

// Class untuk merepresentasikan warung makan
class WarungMakan(val name: String) {
    private val menu = mutableListOf<Food>()

    // Function untuk menambahkan makanan ke menu
    fun addFood(food: Food) {
        menu.add(food)
    }

    // Function untuk menampilkan menu
    fun showMenu() {
        println("Menu di $name:")
        menu.forEachIndexed { index, food -> println("${index + 1}. ${food.name} - Rp${food.price}") }
    }

    // Function untuk memesan makanan
    fun orderFood(index: Int, quantity: Int): Food? {
        return menu.getOrNull(index - 1)?.let { food ->
            if (quantity > 0) food else null
        }
    }
}

fun main() {
    // Membuat objek warung makan
    val warung = WarungMakan("Warung Barokah")

    // Menambahkan makanan ke menu
    warung.addFood(Food("Nasi Goreng", 15000))
    warung.addFood(Food("Mie Ayam", 12000))
    warung.addFood(Food("Sate Ayam", 20000))
    warung.addFood(Food("Gado-Gado", 10000))

    // Menampilkan menu
    warung.showMenu()

    // Mengambil input dari pengguna
    println("\nMasukkan nomor makanan yang ingin dipesan:")
    val foodIndex = readLine()?.toIntOrNull() ?: 0

    // Memilih jumlah makanan yang akan dibeli
    println("Masukkan jumlah yang ingin dibeli:")
    val quantity = readLine()?.toIntOrNull() ?: 0

    // Memeriksa apakah makanan tersedia
    val food = warung.orderFood(foodIndex, quantity)
    if (food != null) {
        val total = food.price * quantity
        println("Pesanan Anda: ${food.name}. Total Harga: Rp$total")

        // Memasukkan uang tunai
        println("Masukkan uang tunai:")
        val cash = readLine()?.toIntOrNull() ?: 0

        // Menghitung kembalian
        val change = cash - total
        if (change >= 0) {
            println("Kembalian Anda: Rp$change")
        } else {
            println("Maaf, uang tunai tidak mencukupi.")
        }
    } else {
        println("Maaf, makanan tidak tersedia.")
    }
}
