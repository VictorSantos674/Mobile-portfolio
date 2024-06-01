package br.com.victor.pwm.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victor.pwm.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF114B5F))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Portfolio",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Adicionando a minha foto
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )

            // Seção "Sobre mim"
            Text(
                text = "Sobre mim:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Victor Souza Santos, estudante de Ciência da Computação, com estágio no Tribunal de Justiça de Pernambuco em 2023." +
                        " Busco oportunidades em Desenvolvimento de Software, valorizando inovação e crescimento profissional. Interessado em novas tecnologias e soluções que transformem a sociedade."
                ,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Seção "Interatividade"
            Text(
                text = "Interatividade:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            var resposta by remember { mutableStateOf("") }
            TextField(
                value = resposta,
                onValueChange = { novoValor -> resposta = novoValor },
                label = { Text(text = "Answer") }
            )

            // Seção "Habilidades"
            Text(
                text = "Habilidades:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.java),
                contentDescription = "Java",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Java - avançado",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.html),
                contentDescription = "HTML5",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "HTML5 - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.css),
                contentDescription = "CSS3",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "CSS3 - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.java_script),
                contentDescription = "JavaScript",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "JavaScript - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.excel),
                contentDescription = "Excel",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Excel - avançado",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.python),
                contentDescription = "Python",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Python - básico",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.sql),
                contentDescription = "SQL",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "SQL - intermediário",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "C++",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "C++ - básico",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.kotlin),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Kotlin - básico",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // Seção "Contatos"
            Text(
                text = "Contatos:",
                style = TextStyle(
                    color = Color(0xFF028090),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Email: 3.victorsouza@gmail.com\nLinkedIn: https://www.linkedin.com/in/victor-santos-59a886220/\nGitHub: https://github.com/VictorSantos674",
                style = TextStyle(
                    color = Color(0xFF456990),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MyApplicationTheme {
        App()
    }
}