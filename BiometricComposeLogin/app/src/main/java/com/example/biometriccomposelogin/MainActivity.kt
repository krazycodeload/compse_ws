package com.example.biometriccomposelogin

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.biometriccomposelogin.ui.theme.BiometricComposeLoginTheme

//class MainActivity : ComponentActivity() {
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val biometricAuthenticator = BiometricAuthenticator(this)



        setContent {
            BiometricComposeLoginTheme {
               Surface(
                   modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.background
               ){
                   val activity = LocalContext.current as FragmentActivity
                   var message by remember {
                       mutableStateOf("")
                   }

                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       TextButton(
                           onClick = {
                               biometricAuthenticator.promptBiometricAuth(
                                   title = "Login",
                                   subTitle = "Use your fingerprint",
                                   negativeButtonText = "Cancel",
                                   fragmentActivity = activity,
                                   onSuccess = {
                                       message = "Success"
                                   },
                                   onError = { _, errorString ->
                                       message = errorString.toString()
                                   },
                                   onFailed = {
                                       message = "Verification error"
                                   }
                               )
                           }) {
                           Text(text = "Sign in with fingerprint")
                       }
                       Spacer(modifier = Modifier.height(10.dp))
                       Text(text = message)
                   }
               }
            }
        }
    }
}

