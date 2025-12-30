package com.eunbi.character.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eunbi.character.R
import com.eunbi.character.model.Character

@Composable
fun ListItem(item: Character, itemClickListener: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .background(Color.White)
            .clickable {
                itemClickListener.invoke()
            }
    ) {
        val (image, name, status, gender) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .placeholder(R.drawable.ic_empty)
                .error(R.drawable.ic_empty)
                .crossfade(true)
                .build(),
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(gender.bottom)
                }
                .clickable {
                    itemClickListener.invoke()
                },
            contentScale = ContentScale.Crop
        )

        Text(
            text = item.name,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(image.end, 16.dp)
                    top.linkTo(image.top)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = item.status,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(status) {
                    start.linkTo(image.end, 16.dp)
                    top.linkTo(name.bottom, 4.dp)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = item.gender,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(gender) {
                    start.linkTo(image.end, 16.dp)
                    top.linkTo(status.bottom, 4.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}