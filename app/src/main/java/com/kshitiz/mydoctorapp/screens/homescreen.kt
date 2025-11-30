package com.kshitiz.mydoctorapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kshitiz.mydoctorapp.R
import com.kshitiz.mydoctorapp.model.Category
import com.kshitiz.mydoctorapp.model.Doctor
import com.kshitiz.mydoctorapp.model.DoctorRepository
import com.kshitiz.mydoctorapp.ui.theme.BackgroundLightBlue
import com.kshitiz.mydoctorapp.ui.theme.BluePrimary
import com.kshitiz.mydoctorapp.ui.theme.CardBg
import com.kshitiz.mydoctorapp.ui.theme.TextBlack
import com.kshitiz.mydoctorapp.ui.theme.TextGray

@Composable
fun HomeScreen(
    onDoctorClick: (Doctor) -> Unit = {}
) {
    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = { BottomNavBar() },
        containerColor = BackgroundLightBlue
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { CategoriesSection() }
            item { DoctorListSection(onDoctorClick) }
        }
    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Hello,",
                style = MaterialTheme.typography.headlineSmall,
                color = TextGray
            )
            Text(
                text = "Chloe F.",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = TextBlack
                )
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = TextBlack
                )
            }
        }
    }
}

@Composable
fun CategoriesSection() {
    val categories = listOf(
        Category(1, "Check-up", R.drawable.checkup), // Placeholder
        Category(2, "Dental", R.drawable.ic_medical_cross), // Placeholder
        Category(3, "Cardiologist", R.drawable.ic_medical_cross) // Placeholder
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium,
                color = BluePrimary,
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            categories.forEach { category ->
                CategoryItem(category)
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.White, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Using Icon for now as placeholders might not exist
            Icon(

                painterResource(id = when (category.name) {
                    "Check-up" -> R.drawable.checkup
                    "Dental" -> R.drawable.tooth // Placeholder for tooth
                    "Cardiologist" -> R.drawable.heart
                    else -> R.drawable.checkup
                }),
                contentDescription = category.name,
                tint = Color.Unspecified,
                modifier = Modifier.size(55.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.name,
            style = MaterialTheme.typography.bodyMedium,
            color = TextBlack,
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorListSection(onDoctorClick: (Doctor) -> Unit) {
    var selectedFilter by remember { mutableStateOf("All doctors") }
    val filters = listOf("All doctors", "General Practitioners", "Cardiologists")
    
    val doctors = DoctorRepository.doctors

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Our doctors",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextBlack
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.bodyMedium,
                color = BluePrimary,
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filters) { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BluePrimary,
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = TextGray
                    ),
                    border = null,
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        doctors.forEach { doctor ->
            DoctorCard(doctor, onDoctorClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor, onClick: (Doctor) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(doctor) },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(doctor.color)
            ) {
                // Placeholder for doctor image
                 Icon(
                    painterResource(id = R.drawable.femaildoc),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center).padding(10.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
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
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_calendar),
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = doctor.distance, // Using distance field for time slot as per design
                        style = MaterialTheme.typography.bodySmall,
                        color = TextGray
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onClick(doctor) },
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(
                            text = "Book now",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(36.dp)
                            .background(BackgroundLightBlue, RoundedCornerShape(12.dp))
                    ) {
                        Icon(
                           painter = painterResource(R.drawable.ic_chatbubbleoutline),
                            contentDescription = "Chat",
                            tint = BluePrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomNavItem("Home", Icons.Outlined.Home),
        BottomNavItem("Calendar", iconResId = R.drawable.ic_calendar),
        BottomNavItem("Chat", iconResId = R.drawable.ic_chatbubbleoutline),
        BottomNavItem("Settings", Icons.Outlined.Settings)
    )

    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(TextBlack)
            .padding(horizontal = 5.dp), // Padding inside the black container
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            
            Box(
                modifier = Modifier
                    .weight(if (isSelected) 1.2f else 1f) // Give more space to selected item
                    .fillMaxHeight()
                    .padding(vertical = 5.dp) // Padding from top/bottom of navbar
                    .clip(RoundedCornerShape(22.dp))
                    .background(if (isSelected) Color.White else Color.Transparent)
                    .clickable { selectedItem = index }
                    .padding(horizontal = 5.dp), // Padding inside the selection pill
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (item.iconVector != null) {
                        Icon(
                            imageVector = item.iconVector,
                            contentDescription = item.label,
                            tint = if (isSelected) TextBlack else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    } else if (item.iconResId != null) {
                        Icon(
                            painter = painterResource(item.iconResId),
                            contentDescription = item.label,
                            tint = if (isSelected) TextBlack else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    androidx.compose.animation.AnimatedVisibility(visible = isSelected) {
                        Row {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.labelLarge,
                                color = TextBlack,
                                maxLines = 1,
                                softWrap = false
                            )
                        }
                    }
                }
            }
        }
    }
}

data class BottomNavItem(
    val label: String,
    val iconVector: ImageVector? = null,
    val iconResId: Int? = null
)

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
