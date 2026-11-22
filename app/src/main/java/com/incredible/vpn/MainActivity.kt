package com.incrediblevpn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF10143A)
private val Purple = Color(0xFF6336C9)
private val Green = Color(0xFF20C879)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { IncredibleVpnApp() }
    }
}

@Composable
fun IncredibleVpnApp() {
    var connected by remember { mutableStateOf(false) }
    var selectedMode by remember { mutableStateOf("Normal Browse Mode") }

    MaterialTheme(colorScheme = darkColorScheme(
        primary = Purple,
        background = Navy,
        surface = Color(0xFF1C2250)
    )) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Navy, Purple.copy(alpha = 0.75f))))
                .padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(28.dp))
            Text("INCREDIBLE VPN", fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Text("FAST & STABLE", color = Color.LightGray, letterSpacing = 3.sp)
            Spacer(Modifier.height(34.dp))
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF202653))) {
                Column(Modifier.fillMaxWidth().padding(18.dp)) {
                    Text("Fastest for Me", color = Color.LightGray)
                    Text("Nigeria • Lagos", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Server selection will be connected to the live server directory.", color = Color.LightGray, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(28.dp))
            Button(
                onClick = { connected = !connected },
                modifier = Modifier.size(220.dp),
                shape = RoundedCornerShape(120.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = Navy)
            ) {
                Text(if (connected) "DISCONNECT" else "TAP TO\nCONNECT", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            }
            Text(
                if (connected) "Demo status only — no VPN tunnel is active" else "Not connected",
                color = Color.White,
                modifier = Modifier.padding(top = 12.dp)
            )
            Spacer(Modifier.height(24.dp))
            Text("CONNECTION MODE", color = Color.White, fontWeight = FontWeight.Bold)
            listOf("Normal Browse Mode", "Facebook Create Mode", "Facebook Page / Switch Mode").forEach { mode ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = selectedMode == mode, onClick = { selectedMode = mode })
                    Text(mode, color = Color.White)
                }
            }
            Spacer(Modifier.weight(1f))
            Text("IP status: Not verified     •     Kill switch: Not enabled", color = Color.LightGray, fontSize = 12.sp)
            Text("Early development preview", color = Color.LightGray, fontSize = 11.sp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
