package com.codewithkael.jetpackcomposewebrtc.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhoToCallLayout(
    modifier: Modifier,
    onStartCallButtonClicked: (String) -> Unit
) {
    val targetUserName = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        OutlinedTextField(
            value = targetUserName.value,
            onValueChange = { targetUserName.value = it },
            label = { Text("Who to call?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp)) // For spacing between the TextField and Button

        Button(
            onClick = {
                onStartCallButtonClicked.invoke(targetUserName.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(
                text = "Call",
                fontSize = 20.sp
            )
        }
    }
}