package com.codewithkael.jetpackcomposewebrtc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codewithkael.jetpackcomposewebrtc.R

@Composable
fun ControlButtonsLayout(
    modifier: Modifier,
    onAudioButtonClicked: (Boolean) -> Unit,
    onCameraButtonClicked: (Boolean) -> Unit,
    onEndCallClicked: () -> Unit,
    onSwitchCameraClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Color.LightGray, // Example color, replace with your actual color or drawable
                shape = RoundedCornerShape(10.dp) // Example shape, replace with your actual shape
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        val audioState = remember { mutableStateOf(true) }
        LaunchedEffect(key1 = audioState.value, block = {
            onAudioButtonClicked.invoke(audioState.value)
        })

        IconButton(onClick = {
            audioState.value = !audioState.value
        }) {
            Image(
                painter = painterResource(
                    if (audioState.value) R.drawable.ic_baseline_mic_24
                    else R.drawable.ic_baseline_mic_off_24
                ),
                contentDescription = "Mic",
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = Color.DarkGray, // Example color, replace with your actual tint
                        shape = RoundedCornerShape(50) // Circle shape
                    )
            )
        }

        val cameraSate = remember { mutableStateOf(true) }
        LaunchedEffect(key1 = cameraSate.value, block = {
            onCameraButtonClicked.invoke(cameraSate.value)
        })
        IconButton(onClick = {
            cameraSate.value = !cameraSate.value
        }) {
            Image(
                painter = painterResource(
                    if (cameraSate.value) R.drawable.ic_baseline_videocam_24
                    else R.drawable.ic_baseline_videocam_off_24
                ),
                contentDescription = "Video",
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = Color.DarkGray, // Example color, replace with your actual tint
                        shape = RoundedCornerShape(50) // Circle shape
                    )
            )
        }

        IconButton(onClick = { onEndCallClicked.invoke() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_call_end_24),
                contentDescription = "End Call",
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = Color.Red, // Example color, replace with your actual tint
                        shape = RoundedCornerShape(50) // Circle shape
                    )
            )
        }

        IconButton(onClick = { onSwitchCameraClicked.invoke() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_cameraswitch_24),
                contentDescription = "Switch Camera",
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        color = Color.DarkGray, // Example color, replace with your actual tint
                        shape = RoundedCornerShape(50) // Circle shape
                    )
            )
        }
    }
}