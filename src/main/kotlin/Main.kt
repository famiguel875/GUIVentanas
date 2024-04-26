import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

/**
 * params:
 * icon -> El icono de la ventana.
 * windowState -> El estado de la ventana secundaria.
 * onClose -> Función que se llama cuando se solicita cerrar la ventana.
 * onClickBackMainWindow -> Acción para volver a la ventana principal y cerrar la ventana actual
 * */

fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState(width = 300.dp, height = 200.dp)
    val secondaryWindowState = rememberWindowState(width = 300.dp, height = 200.dp)
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        MainWindow(
            icon = icon,
            state = mainWindowState,
            onClose = { showMainWindow = false },
            onButtonClick = {
                showMainWindow = false
                showSecondWindow = true
            }
        )
    }

    if (showSecondWindow) {
        SecondWindow(
            icon = icon,
            state = secondaryWindowState,
            onClose = { showSecondWindow = false },
            onButtonClick = {
                showSecondWindow = false
                showMainWindow = true
            }
        )
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}

@Composable
fun MainWindow(icon: BitmapPainter, state: WindowState, onClose: () -> Unit, onButtonClick: () -> Unit) {
    Window(
        onCloseRequest = onClose,
        title = "Ventana Principal",
        icon = icon,
        state = state
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Estás en la Ventana Principal")
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = onButtonClick) {
                Text("Abrir Ventana Secundaria y cerrar esta")
            }
        }
    }
}

@Composable
fun SecondWindow(icon: BitmapPainter, state: WindowState, onClose: () -> Unit, onButtonClick: () -> Unit) {
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        icon = icon,
        state = state
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Estás en la Ventana Secundaria")
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = onButtonClick) {
                Text("Abrir Ventana Principal y cerrar esta")
            }
        }
    }
}

