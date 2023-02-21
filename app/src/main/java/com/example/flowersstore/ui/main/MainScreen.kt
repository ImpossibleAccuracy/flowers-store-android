package com.example.flowersstore.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.flowersstore.R
import com.example.flowersstore.data.ProductRepository
import com.example.flowersstore.data.model.Product
import com.example.flowersstore.ui.view.OutlinedCard
import com.example.flowersstore.ui.view.Rating

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val products by viewModel.products.observeAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadMoreProducts()
    }

    products?.let {
        ProductList(
            items = it,
            loadMore = viewModel::loadMoreProducts
        ) { item ->
            navController.navigate("product/${item.id}")
        }
    }
}

@Composable
fun ProductList(
    items: List<Product>,
    loadMore: (() -> Unit)? = null,
    onProductSelected: ((product: Product) -> Unit)? = null
) {
    val spacing = 10.dp
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(spacing),
        horizontalArrangement = Arrangement.spacedBy(spacing),
    ) {
        itemsIndexed(items = items) { _, item ->
            ProductItem(
                item = item,
                modifier = Modifier.clickable(onClick = {
                    onProductSelected?.invoke(item)
                })
            )
        }

        item {
            LaunchedEffect(key1 = Unit) {
                loadMore?.invoke()
            }
        }
    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    item: Product
) {
    val contentPadding = 12.dp

    OutlinedCard(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = painterResource(R.drawable.baseline_image_24),
                model = item.preview,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "${item.price} руб.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium
                )

                Rating(
                    modifier = Modifier.padding(vertical = 4.dp),
                    rating = 3.5f,
                    maxRating = 5
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductItemPreview() {
    ProductItem(item = ProductRepository.products.first())
}

@Preview(showBackground = true)
@Composable
fun ProductListPreview() {
    ProductList(items = ProductRepository.products)
}

