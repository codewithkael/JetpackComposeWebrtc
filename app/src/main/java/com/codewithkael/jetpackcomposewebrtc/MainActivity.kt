package com.codewithkael.jetpackcomposewebrtc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codewithkael.jetpackcomposewebrtc.ui.components.ControlButtonsLayout
import com.codewithkael.jetpackcomposewebrtc.ui.components.IncomingCallComponent
import com.codewithkael.jetpackcomposewebrtc.ui.components.SurfaceViewRendererComposable
import com.codewithkael.jetpackcomposewebrtc.ui.components.WhoToCallLayout
import com.codewithkael.jetpackcomposewebrtc.ui.theme.JetpackComposeWebrtcTheme
import dagger.hilt.android.AndroidEntryPoint
import org.webrtc.SurfaceViewRenderer
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var localSurfaceViewRenderer: SurfaceViewRenderer? = null
    private var remoteSurfaceViewRenderer: SurfaceViewRenderer? = null

    private val myUsername = UUID.randomUUID().toString().substring(0, 2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeWebrtcTheme {
                val requestPermissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions()
                ) { permissions ->
                    if (permissions.all { it.value }) {
                        //start the process
                        mainViewModel.init(myUsername)
                    }
                }
                LaunchedEffect(key1 = Unit) {
                    requestPermissionLauncher.launch(
                        arrayOf(
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.RECORD_AUDIO,
                        )
                    )
                }


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val incomingCallState =
                            mainViewModel.incomingCallerSection.collectAsState(null)
                        if (incomingCallState.value != null) {
                            IncomingCallComponent(
                                incomingCallerName = incomingCallState.value?.name,
                                modifier = Modifier.weight(1f),
                                onAcceptPressed = mainViewModel::acceptCall,
                                onRejectPressed = mainViewModel::rejectCall
                            )
                        } else {
                            WhoToCallLayout(
                                modifier = Modifier.weight(2f),
                                onStartCallButtonClicked = mainViewModel::startCall
                            )
                        }

                        SurfaceViewRendererComposable(
                            modifier = Modifier.weight(4f),
                            onSurfaceReady = { remoteSurface ->
                                remoteSurfaceViewRenderer = remoteSurface
                                mainViewModel.setRemoteView(remoteSurface)
                            }
                        )

                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
                                .background(color = Color.Gray)
                        )

                        SurfaceViewRendererComposable(
                            modifier = Modifier.weight(4f),
                            onSurfaceReady = { localSurface ->
                                localSurfaceViewRenderer = localSurface
                                mainViewModel.setLocalView(localSurface)
                            }
                        )
                        ControlButtonsLayout(
                            modifier = Modifier.weight(1f),
                            onAudioButtonClicked = mainViewModel::onAudioButtonClicked,
                            onCameraButtonClicked = mainViewModel::onCameraButtonClicked,
                            onEndCallClicked = mainViewModel::onEndCallClicked,
                            onSwitchCameraClicked = mainViewModel::onSwitchCameraClicked,
                        )
                    }
                }
            }
        }
    }
}
