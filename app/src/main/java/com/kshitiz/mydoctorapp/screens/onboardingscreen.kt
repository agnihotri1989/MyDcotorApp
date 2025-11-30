package com.kshitiz.mydoctorapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kshitiz.mydoctorapp.R


@Composable
fun OnBoardingScreen(onGetStarted:()->Unit) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F6FFF)),
        contentAlignment = Alignment.Center
    ) {
        if (screenWidth > 600.dp) {
            // Tablet Layout
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_medical_cross),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 32.dp)
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.onboarding_text),
                        style = MaterialTheme.typography.displaySmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(id = R.string.onboarding_text_trusted),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                    Button(
                        onClick = {onGetStarted },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(65.dp), // A bit taller
                        shape = RoundedCornerShape(30),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                        ),
                        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.get_started),
                                color = Color(0xFF0F6FFF),
                                style = MaterialTheme.typography.titleLarge, // Larger text
                                fontWeight = FontWeight.Bold
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .fillMaxHeight()
                                    .aspectRatio(1f)
                                    .background(Color.Black, shape = RoundedCornerShape(30)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(28.dp) // Larger icon
                                )
                            }
                        }
                    }
                }
            }
        } else {
            // Phone Layout
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_medical_cross),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(240.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.onboarding_text),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(id = R.string.onboarding_text_trusted),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick =
                        {onGetStarted()}
                    ,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(55.dp),
                    shape = RoundedCornerShape(30),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = stringResource(id = R.string.get_started),
                            color = Color(0xFF0F6FFF),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .background(Color.Black, shape = RoundedCornerShape(30)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(22.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, name = "Tablet Preview", device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun OnBoardingScreenTabletPreview() {
   // OnBoardingScreen()
}