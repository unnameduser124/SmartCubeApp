package com.example.smartcubeapp.ui.statsUI

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.smartcubeapp.ui.popups.PopupCenterPositionProvider
import com.example.smartcubeapp.ui.theme.surfaceContainerHighestDark

class OLLCaseImagePopup(val context: Context, val imageID: Int, val popupVisible: MutableState<Boolean>) {

    @Composable
    fun GeneratePopup() {
        Popup(
            onDismissRequest = {
                popupVisible.value = false
            },
            properties = PopupProperties(focusable = true),
            popupPositionProvider = PopupCenterPositionProvider()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = surfaceContainerHighestDark, shape = RoundedCornerShape(20.dp))
                    .widthIn(max = 350.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = "OLL Case Image",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(200.dp)
                )
            }
        }
    }
}