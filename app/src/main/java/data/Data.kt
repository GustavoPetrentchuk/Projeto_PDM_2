package data

import androidx.annotation.DrawableRes
import com.example.projeto2.R

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    @DrawableRes val productImage: Int,
    val isFavorite: Boolean = false
)

object ProductsRepo {
    private val productList = listOf(
        Product(1, "Feijão", "R$ 3.5", R.drawable.arroz),
        Product(2, "Arroz", "R$ 3.0", R.drawable.feijao),
        Product(3, "Leite", "R$ 4.0", R.drawable.leite),
        Product(4, "Café", "R$ 5.0", R.drawable.cafe),
        Product(5, "Pão", "R$ 2.0", R.drawable.pao)
    )

    fun getProducts(): List<Product> = productList
}

//name: String, price: String, productImage: Image
/*fun addNewProduct(name: String, price: String): Product {

    return Product(name, price, R.drawable.pao)
}*/
