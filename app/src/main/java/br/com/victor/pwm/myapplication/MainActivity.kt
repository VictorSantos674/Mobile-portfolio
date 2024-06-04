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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.victor.pwm.myapplication.ui.theme.MyApplicationTheme

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

    NavigationBar(
        containerColor = Color(0xFF028090),
        contentColor = Color.White
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    // Evita navegações repetidas para a mesma rota
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Mantém o estado ao navegar para um novo destino
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Evita criar várias instâncias do mesmo destino
                            launchSingleTop = true
                            // Restaura o estado se estiver disponível
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
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
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
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
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(256.dp)
                    .padding(16.dp)
            )
        }
        item {
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
                text = "Me chamo Victor Souza Santos e sou um estudante de Ciência da Computação",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        item {
            Text(
                text = "Contatos:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                ClickableLink(
                    label = "Email: ",
                    value = "3.victorsouza@gmail.com",
                    url = "mailto:3.victorsouza@gmail.com"
                )
                ClickableLink(
                    label = "LinkedIn: ",
                    value = "https://www.linkedin.com/in/victor-santos-59a886220/",
                    url = "https://www.linkedin.com/in/victor-santos-59a886220/"
                )
                ClickableLink(
                    label = "GitHub: ",
                    value = "https://github.com/VictorSantos674",
                    url = "https://github.com/VictorSantos674"
                )
            }
        }
    }
}

@Composable
fun ClickableLink(label: String, value: String, url: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
    ) {
        Text(
            text = label,
            style = TextStyle(
                color = Color(0xFF028090),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = value,
            style = TextStyle(
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )
    }
}

@Composable
fun AboutMe() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
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
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Projetos:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            projectItem(
                title = "Portfolio - Web",
                imageRes = R.drawable.portfolio_web,
                url = "https://portfolio2024-five-chi.vercel.app/"
            )
            projectItem(
                title = "GitPlus",
                imageRes = R.drawable.gitplus,
                url = "https://projeto-web-streaming.vercel.app/"
            )
            projectItem(
                title = "Educa Livros Online",
                imageRes = R.drawable.educa_livros_online,
                url = "https://github.com/RianDelou/bd-logical-physical"
            )
        }
    }
}

@Composable
fun projectItem(title: String, imageRes: Int, url: String) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = Color(0xFF028090),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
        )
    }
}

@Composable
fun Skills() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "Habilidades",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
            )

            SkillItem(imageRes = R.drawable.java, skillDescription = "Java - avançado")
            SkillItem(imageRes = R.drawable.kotlin, skillDescription = "Kotlin - básico")
            SkillItem(imageRes = R.drawable.html, skillDescription = "HTML5 - intermediário")
            SkillItem(imageRes = R.drawable.css, skillDescription = "CSS3 - intermediário")
            SkillItem(imageRes = R.drawable.java_script, skillDescription = "JavaScript - intermediário")
            SkillItem(imageRes = R.drawable.excel, skillDescription = "Excel - avançado")
            SkillItem(imageRes = R.drawable.python, skillDescription = "Python - básico")
        }
    }
}

@Composable
fun SkillItem(imageRes: Int, skillDescription: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .padding(16.dp)
        )
        Text(
            text = skillDescription,
            style = TextStyle(
                color = Color(0xFF456990),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MyApplicationTheme {
        Home()
    }
}