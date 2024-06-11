package com.example.ph44766_assignment.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ph44766_assignment.R
import com.example.ph44766_assignment.ui.theme.AsmKOT104Theme
import com.example.ph44766_assignment.user.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    navController: NavHostController
) {
    Column {
        var passwordVisible by remember { mutableStateOf(false) }
        val loginUIState = loginViewModel?.loginUIState
        val isError = loginUIState?.signUpError != null
        val context = LocalContext.current

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.lo_si),
                    contentDescription = "Logo",
                    modifier = Modifier.size(width = 320.dp, height = 100.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "WELCOME",
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    fontFamily = FontFamily.Monospace,
                    color = Color(0xFF303030),
                    modifier = Modifier.padding(start = 30.dp)
                )
                Spacer(modifier = Modifier.height(60.dp))
                //Username TextField
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .width(360.dp)
                        .height(500.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        value = loginUIState?.userNameSignUp ?: "",
                        onValueChange = { loginViewModel?.onUserNameChangeSignUp(it) },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = loginUIState?.passwordSignUp ?: "",
                        onValueChange = { loginViewModel?.onPasswordChangeSignUp(it) },
                        label = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
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
                    Spacer(modifier = Modifier.height(8.dp))
                    //Password TextField
                    OutlinedTextField(
                        value = loginUIState?.confirmPasswordSignUp ?: "",
                        onValueChange = { loginViewModel?.onConfirmPasswordChangeSignUp(it) },
                        label = { Text("Re-Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                    )
                    if (loginUIState?.isLoading == true) {
                        CircularProgressIndicator()
                    }
                    LaunchedEffect(key1 = loginViewModel?.signUpSuccessful) {
                        if (loginViewModel?.signUpSuccessful == true) {
                            navController.navigate("login")
                        }
                    }
                    if (isError) {
                        Text(
                            text = loginUIState?.signUpError ?: "unknown error ",
                            color = Color.Red,
                            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))//Khoang cach
                    Button(
                        onClick = {
                            loginViewModel?.createUser(context)
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                    ) {
                        Text("SIGN UP")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = "Already have account? ", color = Color(0x80303030),
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "LOGIN", color = Color(0xFF303030),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.clickable {
                                navController.navigate("login")
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
fun SignUpScreenPreview() {
    AsmKOT104Theme {
        SignUpScreen(navController = rememberNavController(), loginViewModel = null)
    }
}