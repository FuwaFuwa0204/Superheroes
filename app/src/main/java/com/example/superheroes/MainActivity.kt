package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    heroApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun heroApp(){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { heroTopBar()}
    ){
        LazyColumn(modifier= Modifier
            .padding(it)
            .background(MaterialTheme.colorScheme.background)){
            items(heroes){
                heroCard(hero=it)
            }


        }
    }



}

@Composable
fun heroTopBar( modifier: Modifier = Modifier
){
    Row(modifier=modifier.fillMaxWidth().height(70.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        Text(
            text = "Superheroes",
            style = MaterialTheme.typography.displayLarge
        )

    }

}

//여기서 히어로 클래스 통으로 불러오기
@Composable
fun heroCard(hero: Hero, modifier: Modifier =Modifier){
    Card(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        .height(100.dp),
         colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
         elevation = CardDefaults.cardElevation(2.dp)){
        Row(modifier= modifier
            .fillMaxSize()
            .padding(16.dp)){
            heroInformation(hero.nameRes, hero.descriptionRes, Modifier.weight(1f))
            Spacer(modifier.width(20.dp))
            heroIcon(hero.imageRes, Modifier.weight(1f))
        }

    }
}

@Composable
fun heroInformation(@StringRes heroName:Int, @StringRes heroDescription:Int, modifier: Modifier = Modifier){
    Column(modifier=modifier){
        Text(stringResource(heroName),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displaySmall)
        Text(stringResource(heroDescription),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge)
    }
}
@Composable
fun heroIcon(@DrawableRes heroImage:Int, modifier: Modifier =Modifier){
    Image(painterResource(heroImage),
        contentDescription =null,
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun superheroPreview() {
    SuperheroesTheme(darkTheme = true) {
        heroApp()
    }
}