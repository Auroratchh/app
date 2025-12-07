package com.example.gamesapp.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

/***
 * Project: Room App
 * Package: com.daniel.roomapp.shared
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/04/2025 - 09:02
 * All rights reserved 2025.
 **/

@Composable
fun MainButton(
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    buttonTitle: String,
    icon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = buttonTitle)
        }
        Text(buttonTitle)
    }
}

@Composable
fun MainOutlinedButton(
    onClick: () -> Unit,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = Color.Black,
    buttonTitle: String,
    icon: ImageVector? = null,
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor,
        ),
        border = BorderStroke(0.5.dp, borderColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = buttonTitle)
        }
        Text(buttonTitle)
    }
}

@Composable
fun MainIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    tint: Color
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(icon, contentDescription = "Icon Button", tint = tint)
    }
}

@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add"
        )
    }
}

@Composable
fun CircleButton(
    icon: ImageVector,
    isEnabled: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        contentPadding = PaddingValues(8.dp),
        enabled = isEnabled,
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon Button",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(24.dp)

        )
    }
}
