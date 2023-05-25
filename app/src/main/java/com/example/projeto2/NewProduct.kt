package com.example.projeto2

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projeto2.ui.theme.Projeto2Theme
import data.Product

@Composable
fun NewProduct(productsViewModel: ProductsViewModel, navController: NavHostController) {
    val nome = remember { mutableStateOf("") }
    val preco = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Novo Produto") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        content = { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colors.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(Modifier.padding(bottom = 8.dp)) {
                        OutlinedTextField(
                            value = nome.value,
                            onValueChange = { nome.value = it },
                            label = { Text("Nome") },
                            modifier = Modifier.widthIn(max = 250.dp)
                        )
                    }

                    Row(Modifier.padding(bottom = 8.dp)) {
                        OutlinedTextField(
                            value = preco.value,
                            onValueChange = { preco.value = it },
                            label = { Text("Preço") },
                            modifier = Modifier.widthIn(max = 250.dp)
                        )
                    }

                    Row(modifier = Modifier.padding(top = 16.dp)) {
                        Button(
                            onClick = {
                                val nomeProduto = nome.value
                                val precoProduto = preco.value
                                if (nomeProduto.isNotBlank() && precoProduto.isNotBlank()) {
                                    val novoProduto = Product(
                                        id = generateNewProductId(),
                                        name = nomeProduto,
                                        price = precoProduto,
                                        productImage = R.drawable.new_product
                                    )
                                    productsViewModel.addProduct(novoProduto)
                                    navController.popBackStack()
                                }
                            },
                            modifier = Modifier.widthIn(max = 185.dp)
                        ) {
                            Text("Adicionar Produto")
                        }
                    }
                }
            }
        }
    )
}

private var currentProductId = 0

fun generateNewProductId(): Int {
    currentProductId++
    return currentProductId
}


/*@Preview
@Composable
fun NewProductPreview() {
    val productsViewModel = ProductsViewModel()
    val navController = rememberNavController()

    Projeto2Theme {
        NewProduct(productsViewModel = productsViewModel, navController = navController)
    }
}*/






