package de.example.navbugapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun BottomNav(startScreen: Int, callback: (index: Int)->Unit) {

    val currentScreen = remember { mutableIntStateOf(startScreen) }

    fun itemTapped(index: Int) {
        if(currentScreen.intValue == index) return
        currentScreen.intValue = index
        callback(index)
    }

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        itemsIndexed(navItemTexts) { index, _ ->
            val interactionSource = remember { MutableInteractionSource() }
            ConstraintLayout(modifier = Modifier
                .height(60.dp)
                .background(if (currentScreen.intValue == index) Color.Yellow else Color.White)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) { itemTapped(index) }
            ) {
                val (textRef) = createRefs()
                Text(
                    navItemTexts[index],
                    fontSize = 14.sp,
                    fontWeight = if (currentScreen.intValue == index) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.constrainAs(textRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.value(80.dp)
                    }
                )
            }
        }
    }
}

val navItemTexts = listOf(
    "FEED",
    "TEST",
    "PROFILE",
)

enum class BottomNavigationRoute(val route: String) {
    FEED("PRIMARY"),
    TEST("TEST"),
    PROFILE("PROFILE"),
}