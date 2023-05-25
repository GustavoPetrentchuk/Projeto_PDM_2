package com.example.projeto2

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import data.Product

@Composable
fun Home(productsViewModel: ProductsViewModel, navController: NavHostController) {
    val products = productsViewModel.products

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Destination.NewProduct.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item {
                Header(text = "Lista de Produtos")
            }
            items(products) { product ->
                ProductListItem(
                    product = product,
                    navController = navController,
                    productsViewModel = productsViewModel,
                    isFavorite = product.isFavorite
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp), color = Color.Black)
            }
        }
    }
}

@Composable
fun ProductListItem(
    product: Product,
    navController: NavHostController,
    productsViewModel: ProductsViewModel,
    isFavorite: Boolean
) {
    val onProductDeleted: () -> Unit = {
        productsViewModel.removeProduct(product)
        navController.navigateUp()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(Destination.Details.createRoute(product.id))
            }
            .border(1.dp, Color.Black, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        val imageModifier = Modifier
            .size(135.dp)
            .padding(end = 16.dp)
        Image(
            painter = painterResource(id = product.productImage),
            contentDescription = null,
            modifier = imageModifier,
        )
        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.body2,
                color = Color.Black.copy(0.5f)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = onProductDeleted
        ) {
            Icon(Icons.Default.Delete, contentDescription = "Excluir Produto")
        }
        IconButton(
            onClick = { productsViewModel.toggleFavorite(product) },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            val favoriteIcon = if (isFavorite) {
                Icons.Default.Favorite
            } else {
                Icons.Default.FavoriteBorder
            }
            Icon(favoriteIcon, contentDescription = "Favorito")
        }
    }
}

@Composable
fun Header(text: String) {
    TopAppBar(
        title = {
            Text(
                text = text,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
        elevation = AppBarDefaults.TopAppBarElevation
    )
}