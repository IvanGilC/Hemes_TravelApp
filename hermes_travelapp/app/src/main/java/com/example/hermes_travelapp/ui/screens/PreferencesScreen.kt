package com.example.hermes_travelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hermes_travelapp.ui.theme.Hermes_travelappTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferencesScreen(onBack: () -> Unit = {}) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var emailUpdatesEnabled by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var selectedCurrency by remember { mutableStateOf("EUR (€)") }
    var selectedDateFormat by remember { mutableStateOf("DD/MM/YYYY") }
    var selectedTextSize by remember { mutableStateOf("Medium") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preferences", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Section: App Settings
            Text(
                text = "App Settings",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp)
            )

            PreferenceItem(
                title = "Language",
                subtitle = selectedLanguage,
                icon = Icons.Default.Language,
                onClick = { /* Mock: Open Language Dialog */ }
            )

            PreferenceItem(
                title = "Currency",
                subtitle = selectedCurrency,
                icon = Icons.Default.Payments,
                onClick = { /* Mock: Open Currency Dialog */ }
            )

            PreferenceItem(
                title = "Date Format",
                subtitle = selectedDateFormat,
                icon = Icons.Default.CalendarToday,
                onClick = { /* Mock: Open Date Format Dialog */ }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))

            // Section: Appearance
            Text(
                text = "Appearance",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )

            PreferenceSwitchItem(
                title = "Dark Mode",
                subtitle = if (isDarkMode) "Enabled" else "Disabled",
                icon = Icons.Default.DarkMode,
                checked = isDarkMode,
                onCheckedChange = { isDarkMode = it }
            )

            PreferenceItem(
                title = "Text Size",
                subtitle = selectedTextSize,
                icon = Icons.Default.FormatSize,
                onClick = { /* Mock: Open Text Size Dialog */ }
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f))

            // Section: Notifications
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
            )

            PreferenceSwitchItem(
                title = "Push Notifications",
                subtitle = "Receive alerts about your trips",
                icon = Icons.Default.NotificationsActive,
                checked = notificationsEnabled,
                onCheckedChange = { notificationsEnabled = it }
            )

            PreferenceSwitchItem(
                title = "Email Updates",
                subtitle = "Get travel tips and offers",
                icon = Icons.Default.Mail,
                checked = emailUpdatesEnabled,
                onCheckedChange = { emailUpdatesEnabled = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Mock: Save Preferences */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save Changes", color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PreferenceItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun PreferenceSwitchItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Preview(showBackground = true, name = "Prefs Mode Light")
@Composable
fun PreferencesScreenPreviewLight() {
    Hermes_travelappTheme(darkTheme = false) {
        PreferencesScreen()
    }
}

@Preview(showBackground = true, name = "Prefs Mode Dark")
@Composable
fun PreferencesScreenPreviewDark() {
    Hermes_travelappTheme(darkTheme = true) {
        PreferencesScreen()
    }
}