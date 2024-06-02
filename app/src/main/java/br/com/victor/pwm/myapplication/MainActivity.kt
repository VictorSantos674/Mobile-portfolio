package br.com.victor.pwm.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.victor.pwm.myapplication.R.drawable.java_script
import br.com.victor.pwm.myapplication.ui.theme.MyApplicationTheme
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Home")
    object Skills : NavigationItem("skills", Icons.Default.Create, "Skills")
    object Projects : NavigationItem("projects", Icons.Default.Build, "Projects")
    object AboutMe : NavigationItem("aboutme", Icons.Default.Person, "About Me")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Skills,
        NavigationItem.Projects,
        NavigationItem.AboutMe
    )
    BottomNavigation(
        backgroundColor = Color(0xFF028090),
        contentColor = Color.White
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        icon = icon,
        label = label,
        selected = selected,
        onClick = onClick,
        alwaysShowLabel = true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            selectedTextColor = Color.White,
            indicatorColor = Color(0xFF114B5F),
            unselectedIconColor = Color.White.copy(0.7f),
            unselectedTextColor = Color.White.copy(0.7f)
        )
    )
}

@Composable
fun NavigationBarItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    alwaysShowLabel: Boolean,
    colors: NavigationBarItemColors
) {


}

@Composable
fun BottomNavigation(
    backgroundColor: Color,
    contentColor: Color,
    content: @Composable () -> Unit
) {
    NavigationBar(
        containerColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}

fun NavigationBar(containerColor: Color, contentColor: Color, content: @Composable () -> Unit) {
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) { Home() }
        composable(NavigationItem.Skills.route) { Skills() }
        composable(NavigationItem.Projects.route) { Projects() }
        composable(NavigationItem.AboutMe.route) { AboutMe() }
    }
}

@Composable
fun Home() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Portfolio",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        item {
            // Adicionando a minha foto
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            // Seção "Sobre mim"
            Text(
                text = "Sobre mim:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Me chamo Victor Souza Santos e sou estudante de Ciência da Computação",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {

        }

        item {
            // Seção "Contatos"
            Text(
                text = "Contatos:",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "Email: 3.victorsouza@gmail.com\nLinkedIn: https://www.linkedin.com/in/victor-santos-59a886220/\nGitHub: github.com/seuperfil",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun AboutMe() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Sobre mim:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        item {
            Text(
                    text = "Victor Souza Santos, estudante de Ciência da Computação, com estágio no Tribunal de Justiça de Pernambuco em 2023. Busco oportunidades em Desenvolvimento de Software, valorizando inovação e crescimento profissional. Interessado em novas tecnologias e soluções que transformem a sociedade.",
                    style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun Projects() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Seção "Projetos"
            Text(
                text = "Projetos:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Text(
                text = "Aqui estão alguns dos projetos que desenvolvi:",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        
        //Portfolio - Web
        item {
            Text(
                text = "Portfolio - Web"
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Image(){
                painter = painterResource(id = R.drawable.portfolio_web),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://portfolio2024-five-chi.vercel.app/")
                        )
                        context.startActivity(intent)
                    }
            }
        }
        
        //GitPlus
        item {
            Text(
                text = "GitPlus",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Image(){
                painter = painterResource(id = R.drawable.gitplus),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://projeto-web-streaming.vercel.app/")
                        )
                        context.startActivity(intent)
                    }
            }
        }
        
        //Educa Livros Online
        item {
            Text(
                text = "Educa Livros Online",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Image(){
                painter = painterResource(id = R.drawable.educa_livros_online),
                contentDescription = null,
                modifier = Modifier
                    .size(350.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://bd-logical-physical.vercel.app/")
                        )
                        context.startActivity(intent)
                    }
            }
        }
    }
}

@Composable
fun Skills() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Habilidades",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        //Java
        item {
            Image(
                painter = painterResource(id = R.drawable.java),
                contentDescription = "Java",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "Java - avançado",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //Kotlin
        item {
            Image(
                painter = painterResource(id = R.drawable.kotlin),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "Kotlin - básico",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //HTML5
        item {
            Image(
                painter = painterResource(id = R.drawable.html),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "HTML5 - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //CSS3
        item {
            Image(
                painter = painterResource(id = R.drawable.css),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "CSS3 - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //JavaScript
        item {
            Image(
                painter = painterResource(id = java_script),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "JavaScript - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //Excel
        item {
            Image(
                painter = painterResource(id = R.drawable.excel),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "Excel - avançado",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        //Python
        item {
            Image(
                painter = painterResource(id = R.drawable.python),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
        item {
            Text(
                text = "Python - básico",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MyApplicationTheme {
        Home()
    }
}