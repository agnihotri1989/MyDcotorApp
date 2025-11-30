package com.kshitiz.mydoctorapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kshitiz.mydoctorapp.R
import com.kshitiz.mydoctorapp.model.Doctor
import com.kshitiz.mydoctorapp.ui.theme.BackgroundLightBlue
import com.kshitiz.mydoctorapp.ui.theme.BluePrimary
import com.kshitiz.mydoctorapp.ui.theme.TextBlack
import com.kshitiz.mydoctorapp.ui.theme.TextGray

@Composable
fun DoctorDetailScreen(
    doctor: Doctor,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { DetailTopBar(onBackClick) },
        containerColor = BackgroundLightBlue,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                ) {
                    Text(
                        text = "Book now",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DoctorInfoSection(doctor)
            Spacer(modifier = Modifier.height(24.dp))
            AppointmentSlotSection()
            Spacer(modifier = Modifier.height(24.dp))
            ReasonSection()
            Spacer(modifier = Modifier.height(100.dp)) // Space for bottom button
        }
    }
}

@Composable
fun DetailTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = TextBlack
            )
        }
        Text(
            text = "Appointment",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextBlack
        )
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
                tint = TextBlack
            )
        }
    }
}

@Composable
fun DoctorInfoSection(doctor: Doctor) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(doctor.color)
            ) {
                 Icon(
                     painterResource(id = R.drawable.femaildoc),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp)
                        .align(Alignment.Center),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = doctor.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextBlack
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = doctor.rating.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = TextBlack
                        )
                    }
                }
                Text(
                    text = doctor.specialty,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextGray
                )
            }
        }
    }
}

@Composable
fun AppointmentSlotSection() {
    var selectedDate by remember { mutableStateOf(14) }
    var selectedTime by remember { mutableStateOf("12:00 am") }

    val dates = listOf(12, 13, 14, 15, 16, 17)
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val times = listOf("10:00 am", "12:00 am", "02:00 pm", "03:00 pm")

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Appointment slot",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextBlack
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(dates.indices.toList()) { index ->
                DateCard(
                    date = dates[index],
                    day = days[index],
                    isSelected = dates[index] == selectedDate,
                    onClick = { selectedDate = dates[index] }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(times) { time ->
                TimeChip(
                    time = time,
                    isSelected = time == selectedTime,
                    onClick = { selectedTime = time }
                )
            }
        }
    }
}

@Composable
fun DateCard(date: Int, day: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(60.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) BluePrimary else Color.White)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSelected) Color.White else TextGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = date.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) Color.White else TextBlack
        )
    }
}

@Composable
fun TimeChip(time: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) BluePrimary else Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) Color.White else TextBlack,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ReasonSection() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Reason for doctor's visit",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextBlack
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text("Describe your symptoms & complaints", color = TextGray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.White, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Preview
@Composable
private fun DoctorDetailScreenPreview() {
    DoctorDetailScreen(
        Doctor(
            1, "Helena Mcneil", "General Practitioner", 4.9, "09:00 am - 02:00 pm",
            R.drawable.ic_medical_cross, Color(0xFFE8F1FF)
        )
    )
}
