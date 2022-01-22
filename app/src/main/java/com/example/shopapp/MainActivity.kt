package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.shopapp.ui.theme.ShopAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopAppTheme {
                ShopHomeScreen()
            }
        }
    }
}

@Composable
@Preview()
fun ShopHomeScreen() {
    val shoeList = listOf(
        Shoe("Rs. 499", R.color.lightPink, R.drawable.shoe1),
        Shoe("Rs. 899", R.color.lightBlue, R.drawable.shoe2),
        Shoe("Rs. 999", R.color.lightIndigo, R.drawable.shoe3)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.backgroundBlue))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black
            )
        }
        buildSpacer()
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Proche Design",
            style = TextStyle(color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )

        buildSpacer()
        Text(
            text = "List of all imported design shoes",
            style = TextStyle(fontSize = 14.sp, color = Color.Black)
        )
        buildSpacer()

        for(c in shoeList){
            buildSingleShoeCard(shoe = c)
            buildSpacer()
        }

    }
}

@Composable
fun buildSingleShoeCard(shoe: Shoe) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        val (addButton, bgImage, shoesImage, priceTag) = createRefs()

        Surface(
            Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(50.dp))
                .constrainAs(bgImage) {
                    start.linkTo(parent.start, margin = 8.dp)
                }, color = colorResource(id = shoe.color)
        ) {

        }

        Box(
            modifier = Modifier
                .height(46.dp)
                .width(46.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .constrainAs(
                    addButton
                ) {
                    top.linkTo(parent.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .constrainAs(shoesImage) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start, margin = 30.dp)
                }
                .rotate(-25f),
            painter = painterResource(id = shoe.image),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .constrainAs(priceTag) {
                    end.linkTo(parent.end, margin = 30.dp)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                },
            text = shoe.price,
            style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

    }
}

data class Shoe(val price: String, val color: Int, val image: Int)


@Composable
fun buildSpacer() {
    Spacer(Modifier.height(10.dp))
}