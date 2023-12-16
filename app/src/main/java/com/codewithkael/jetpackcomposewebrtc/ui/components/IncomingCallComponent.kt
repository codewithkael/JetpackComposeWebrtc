package com.codewithkael.jetpackcomposewebrtc.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithkael.jetpackcomposewebrtc.R

@Composable
fun IncomingCallComponent(
    modifier: Modifier,
    incomingCallerName:String?=null,
    onAcceptPressed:()->Unit,
    onRejectPressed:()->Unit,

) {
    if (incomingCallerName!=null){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
                .border(0.5.dp, color = Color.LightGray)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$incomingCallerName is calling",
                modifier = Modifier
                    .weight(10f)
                    .fillMaxHeight()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Black
            )

            Image(
                painter = painterResource(id = R.drawable.ic_accept),
                contentDescription = "Accept Call",
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .clickable { onAcceptPressed.invoke() }
            )

            Image(
                painter = painterResource(id = R.drawable.ic_reject),
                contentDescription = "Reject Call",
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .clickable { onRejectPressed.invoke() }
            )
        }
    }
}