package com.adrc95.unsplashsample.ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.adrc95.domain.model.Camera
import com.adrc95.unsplashsample.R

@Composable
fun CameraDetailDialog(camera: Camera, onCancel: () -> Unit) {
    Dialog(onDismissRequest = { onCancel() }) {
        Card {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.spacing))
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.camera),
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = camera.name,
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = dimensionResource(id = R.dimen.spacing_medium))
                )
                Text(
                    text = stringResource(id = R.string.lens),
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "ISO ${camera.iso}",
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "${camera.focalLength}mm f/${camera.aperture}",
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${camera.exposureTime}s",
                    fontSize = dimensionResource(id = R.dimen.title).value.sp,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}