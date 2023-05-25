package com.example.projeto2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projeto2.ui.theme.Projeto2Theme
import data.Product

@Composable
fun Details(product: Product, onProductDeleted: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalhes do Produto") },
            )
        },
        content = { innerPadding ->
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = product.price,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface.copy(0.5f)
                    )
                    Image(
                        painter = painterResource(id = product.productImage),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(200.dp)
                            .border(BorderStroke(1.dp, MaterialTheme.colors.onSurface))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onProductDeleted,
                        modifier = Modifier.widthIn(max = 185.dp)
                    ) {
                        Text("Excluir Produto")
                    }
                }
            }
        }
    )
}

/*@Preview
@Composable
fun DetailsPreview() {
    val product = Product(
        id = 1,
        name = "Feijão",
        price = "R$ 3.5",
        productImage = R.drawable.feijao
    )

    Projeto2Theme {
        Details(product, {})
    }
}*/