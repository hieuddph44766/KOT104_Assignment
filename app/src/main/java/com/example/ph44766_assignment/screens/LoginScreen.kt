package com.example.ph44766_assignment.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ph44766_assignment.R
import com.example.ph44766_assignment.ui.theme.AsmKOT104Theme
import com.example.ph44766_assignment.user.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    navController: NavHostController
) {
    Column {
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        var (checked, onCheckedChange) = rememberSaveable { mutableStateOf(false) }
        val loginUIState = loginViewModel?.loginUIState
        val isError = loginUIState?.loginError != null
        val context = LocalContext.current


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Divider(
                            color = Color("#BDBDBD".toColorInt()),
                            thickness = 2.dp,
                            modifier = Modifier.width(105.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier.size(75.dp)
                        )
                        Divider(
                            color = Color("#BDBDBD".toColorInt()),
                            thickness = 2.dp,
                            modifier = Modifier.width(105.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    StyledText()
                }
                Spacer(modifier = Modifier.height(60.dp))
                //Username TextField
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .width(360.dp)
                        .height(450.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = loginUIState?.userName ?: "",
                        onValueChange = { loginViewModel?.onUserNameChange(it) },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //Password TextField
                    OutlinedTextField(
                        value = loginUIState?.password ?: "",
                        onValueChange = { loginViewModel?.onPasswordChange(it) },
                        label = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                painterResource(id = R.drawable.eye2) // Replace with your visible icon
                            else
                                painterResource(id = R.drawable.eye) // Replace with your invisible icon

                            Icon(
                                painter = image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(width = 20.dp, height = 20.dp)
                                    .clickable { passwordVisible = !passwordVisible }
                            )
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))//Khoang cach
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Forgot password?",
                                fontSize = 13.sp,
                                fontWeight = FontWeight(700),
                                fontFamily = FontFamily.Default,
                                color = Color(0xFF303030),
                                modifier = Modifier.padding(
                                    start = 100.dp
                                )
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            navController.navigate("bottom")
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text("LOGIN")
                    }
                    if (loginUIState?.isLoading == true) {
                        CircularProgressIndicator()
                    }
                    LaunchedEffect(key1 = loginViewModel?.loginSuccessful) {
                        if (loginViewModel?.loginSuccessful == true) {
                            navController.navigate("bottom")
                        }
                    }
                    if (isError) {
                        Text(
                            text = loginUIState?.loginError ?: "unknown error ",
                            color = Color.Red,
                            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = "SIGNUP",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFF303030),
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("signup")
                                }
                        )
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AsmKOT104Theme {
        LoginScreen(
            navController = rememberNavController(),
            loginViewModel = null,
        )
    }
}

@Composable
fun StyledText() {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(
                lineHeight = 50.sp
            )
        ) {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 27.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = FontFamily(Font(R.font.gelasio_bold))
                )
            ) {
                append("Hello ! \n")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontSize = 27.sp,
                    fontWeight = FontWeight(600),
                    fontFamily = FontFamily(Font(R.font.gelasio_bold))
                )
            ) {
                append("WELCOME BACK")
            }
        }
    }

    BasicText(
        text = annotatedText,
        modifier = Modifier.width(300.dp),
    )
}