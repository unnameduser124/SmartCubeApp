package com.example.smartcubeapp.ui.popups

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.smartcubeapp.R

class ConfirmationPopup(val context: Context, private val popupVisible: MutableState<Boolean>) {

    private lateinit var onYesClick: () -> Unit

    @Composable
    fun GeneratePopup(onYesClick: () -> Unit) {
        this.onYesClick = onYesClick
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = {
                popupVisible.value = false
            },
            properties = PopupProperties(focusable = true)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
                    .widthIn(max = 300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AreYouSureTextRow()
                YesNoButtonsRow()
            }
        }
    }

    @Composable
    fun AreYouSureTextRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 45.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = context.getString(R.string.confirmation_popup_question),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp),
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun YesNoButtonsRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ChoiceButton(text = "Yes", buttonColor = Color.Red, textColor = Color.White){
                onYesClick()
            }
            ChoiceButton(text = "No", buttonColor = Color.LightGray, textColor = Color.Black)
        }
    }

    @Composable
    fun ChoiceButton(text: String, buttonColor: Color, textColor: Color, onClick: () -> Unit = {}) {
        Button(onClick = {
            onClick()
            popupVisible.value = false
        }, colors = ButtonDefaults.buttonColors(containerColor = buttonColor)) {
            Text(text = text, fontSize = 20.sp, color = textColor)
        }
    }
}

@Preview
@Composable
fun ConfirmationPopupPreview() {
    val context = LocalContext.current
    val popupVisible = remember { mutableStateOf(true) }
    ConfirmationPopup(context, popupVisible).GeneratePopup{}
}