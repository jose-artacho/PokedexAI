package com.artdevs.pokedexai

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScanScreen(
    pokedexViewModel: PokedexViewModel = viewModel()
) {
    val uiState by pokedexViewModel.uiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color(0xFFc32c33))
            .fillMaxSize()
    ) {
        TopSection()
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))

        ScreenSection(pokedexViewModel) {
            // onError
        }

        Row(modifier = Modifier.padding(36.dp)) {

            CaptureButton()

            Spacer(Modifier.weight(1f))

            DpadShape()
        }
    }
}

@Composable
fun TopSection() {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp, bottom = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        CircleButton(Color.Blue, 48, true)
        Spacer(modifier = Modifier.padding(end = 8.dp))
        CircleButton(Color.Red)
        CircleButton(Color.Green)
        CircleButton(Color.Yellow)
    }
}

@Composable
fun CircleButton(color: Color, size: Int = 16, border: Boolean = false) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .border(4.dp, if (border) Color.White else Color.Transparent, CircleShape)
            .background(color, shape = CircleShape)

    )
}

@Composable
fun ScreenSection(
    viewModel: PokedexViewModel,
    onErrorClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(8.dp))
            .clip(CutCornerShape(bottomStart = 50.dp))
            .background(Color.LightGray)
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 64.dp)
                .fillMaxWidth(),
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        ) {
            /* when (pokemonDetail) {
                 is UiState.Success ->
                   //  SuccessView(viewModel.selectedPokemon.value, pokemonDetail.data)

                 is UiState.Error ->
                   //  ErrorView(pokemonDetail.message) { onErrorClick() }

                 else ->
                   //  CameraView()
             }*/
        }
    }
}

// D-pad

@Composable
fun DpadShape() {
    Canvas(
        modifier = Modifier
            .size(100.dp)
    ) {
        val center = Offset(size.width / 2, size.height / 2)
        val buttonSize = size.width / 3
        drawRect(
            color = Color.Black,
            topLeft = Offset(center.x - buttonSize / 2, 0f),
            size = androidx.compose.ui.geometry.Size(buttonSize, size.height)
        )
        drawRect(
            color = Color.Black,
            topLeft = Offset(0f, center.y - buttonSize / 2),
            size = androidx.compose.ui.geometry.Size(size.width, buttonSize)
        )
    }
}

@Composable
fun CaptureButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .clip(CircleShape)
            .size(65.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.icon_camera),
            contentDescription = "Camera",
            tint = Color.White
        )
    }
}
