package com.codewithkael.jetpackcomposewebrtc.ui.components

import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.codewithkael.jetpackcomposewebrtc.R
import org.webrtc.SurfaceViewRenderer

@Composable
fun SurfaceViewRendererComposable(
    modifier: Modifier,
    onSurfaceReady:(SurfaceViewRenderer)->Unit
) {
    // Create the SurfaceViewRenderer
    AndroidView(
        modifier = modifier
            .fillMaxWidth(),
        factory = { ctx ->
                  FrameLayout(ctx).apply {
                      addView(SurfaceViewRenderer(ctx).also {
                          onSurfaceReady.invoke(it)
                      })
                  }
        }
    )
}