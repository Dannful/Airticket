package com.github.dannful.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

private const val EXTENSION = "lfa"
private fun retrieveFile(window: ComposeWindow): File? {
    val fileChooser = JFileChooser()
    fileChooser.fileFilter = FileNameExtensionFilter("Automaton files", EXTENSION)
    fileChooser.showOpenDialog(window)
    return fileChooser.selectedFile
}

@Composable
@Preview
fun MainScreen(window: ComposeWindow, onManualInput: (File?) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Welcome! Choose your interaction method:")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            Button(onClick = {
                onManualInput(null)
            }) {
                Text(text = "Manual input")
            }
            Button(onClick = {
                val file = retrieveFile(window)
                if (file != null) {
                    onManualInput(file)
                }
            }) {
                Text(text = "Load file")
            }
        }
    }
}
