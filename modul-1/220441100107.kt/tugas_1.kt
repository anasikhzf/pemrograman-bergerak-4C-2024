import kotlin.system.exitProcess

class Food(val name: String, val price: Int)

class WarungMakan(val name: String) {
    private val menu = mutableListOf<Food>()
    fun addFood(food: Food) {
        menu.add(food)
    }
    fun showMenu() {
        println("Menu di $name:")
        menu.forEachIndexed { index, food -> println("${index + 1}. ${food.name} - Rp${food.price}") }
    }
    fun orderFood(index: Int, quantity: Int): Food? {
        return menu.getOrNull(index - 1)?.let { food ->
            if (quantity > 0) food else null
        }
    }
}

fun main() {
    val warung = WarungMakan("Warung Sate Aselole")
    warung.addFood(Food("Nasi", 3000))
    warung.addFood(Food("Gule", 17500))
    warung.addFood(Food("Sate Ayam", 10000))
    warung.addFood(Food("Sate Kambing", 15500))
    warung.addFood(Food("Sate Kuda", 16000))
    warung.addFood(Food("Es Teh", 3000))
    warung.addFood(Food("Es Jeyuk", 4000))
    
    val pesanan = mutableListOf<Pair<Food, Int>>()
    while (true) {
        warung.showMenu()
        println("\nMasukkan nomor makanan yang ingin dipesan atau 0 untuk keluar:")
        val foodIndex = readLine()?.toIntOrNull() ?: 0
        if (foodIndex == 0) {
            break
        } else if (foodIndex > 7 || foodIndex < 0) {
            println("Maaf, menu tidak tersedia.")
            continue
        }
        println("Masukkan jumlah yang ingin dibeli:")
        val quantity = readLine()?.toIntOrNull() ?: 0
        val food = warung.orderFood(foodIndex, quantity)
        if (food != null) {
            pesanan.add(food to quantity)
            println("\nApakah Anda ingin menambahkan menu lagi? (Ya/Tidak)")
            val tambahMenu = readLine()?.equals("Ya", ignoreCase = true) ?: false
            if (!tambahMenu) {
                break
            }
        } else {
            println("Maaf, makanan tidak tersedia.")
        }
    }
    

    println("\nPesanan Anda:")
    var totalHarga = 0
    pesanan.forEachIndexed { index, pair ->
        val (food, quantity) = pair
        val subtotal = food.price * quantity
        println("${index + 1}. ${food.name} - ${quantity}x Rp${food.price} = Rp$subtotal")
        totalHarga += subtotal
    }
    println("\nTotal Harga: Rp$totalHarga")
    println("Masukkan uang tunai:")
    val cash = readLine()?.toIntOrNull() ?: 0

    val change = cash - totalHarga
    if (change >= 0) {
        println("Kembalian Anda: Rp$change")
    } else {
        println("Maaf, uang tunai tidak mencukupi.")
    }
}
