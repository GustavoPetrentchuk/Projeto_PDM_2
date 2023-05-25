package com.example.projeto2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projeto2.ui.theme.Projeto2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productsViewModel by viewModels<ProductsViewModel>()

        setContent {
            Projeto2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    AppNavigation(navController, productsViewModel)
                }
            }
        }
    }
}

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object NewProduct : Destination("newProduct")
    object Details : Destination("details/{elementId}") {
        fun createRoute(elementId: Int): String = "details/$elementId"
    }
}

@Composable
fun AppNavigation(navController: NavHostController, productsViewModel: ProductsViewModel) {
    //val products = productsViewModel.products

    NavHost(navController, Destination.Home.route) {
        composable(Destination.Home.route) {
            Home(productsViewModel, navController)
        }
        composable(Destination.Details.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getInt("elementId")
            val product = productsViewModel.products.find { it.id == elementId }
            if (product != null) {
                Details(product) {
                    productsViewModel.removeProduct(product)
                    //navController.popBackStack(Destination.Home.route,false)
                }
            } else {
                navController.popBackStack(Destination.Home.route,false)
            }
        }
        composable(Destination.NewProduct.route) {
            NewProduct(productsViewModel, navController)
        }
    }
}