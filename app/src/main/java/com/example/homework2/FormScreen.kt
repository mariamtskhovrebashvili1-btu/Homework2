package com.example.homework2

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework2.ui.theme.Homework2Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val fieldShape = RoundedCornerShape(16.dp)
private val headerColor = Color(0xFF6A1B9A)
private val backgroundColor = Color(0xFFF3E5F5)
private val cardColor = Color(0xFFFFFFFF)
private val accentColor = Color(0xFFE91E63)
private val textColor = Color(0xFF4A148C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var lastNameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            dateState = formatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = backgroundColor,
        bottomBar = {
            Button(
                onClick = {
                    if (nameState.isBlank() ||
                        lastNameState.isBlank() ||
                        emailState.isBlank() ||
                        dateState.isBlank() ||
                        selectedOption.isBlank() ||
                        !isAgreed
                    ) {
                        Toast.makeText(
                            context,
                            "შეავსეთ ყველა ველი!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "მონაცემები გაიგზავნა!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = accentColor,
                    contentColor = Color.White
                )
            ) {
                Text("Submit", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerColor)
                    .padding(24.dp)
            ) {
                Column {
                    Text(
                        text = "სტუდენტის ფორმა",
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color.White
                    )
                    Text(
                        text = "შეავსეთ თქვენი მონაცემები",
                        color = Color(0xFFE1BEE7)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .background(cardColor, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Text("სახელი", color = textColor, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = nameState,
                    onValueChange = { nameState = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("შეიყვანეთ სახელი") },
                    shape = fieldShape,
                    colors = fieldColors()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("გვარი", color = textColor, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = lastNameState,
                    onValueChange = { lastNameState = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("შეიყვანეთ გვარი") },
                    shape = fieldShape,
                    colors = fieldColors()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("იმეილი", color = textColor, fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("example@mail.com") },
                    shape = fieldShape,
                    colors = fieldColors()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("თარიღი", color = textColor, fontWeight = FontWeight.SemiBold)
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = dateState,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("DD/MM/YYYY") },
                        shape = fieldShape,
                        colors = fieldColors()
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showDatePicker = true }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "თქვენი ფავორიტი მიმართულება",
                    color = textColor,
                    fontWeight = FontWeight.SemiBold
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "Android",
                        onClick = { selectedOption = "Android" },
                        colors = RadioButtonDefaults.colors(selectedColor = accentColor)
                    )
                    Text("Android", color = textColor)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "iOS",
                        onClick = { selectedOption = "iOS" },
                        colors = RadioButtonDefaults.colors(selectedColor = accentColor)
                    )
                    Text("iOS", color = textColor)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOption == "Web",
                        onClick = { selectedOption = "Web" },
                        colors = RadioButtonDefaults.colors(selectedColor = accentColor)
                    )
                    Text("Web", color = textColor)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ვეთანხმები წესებს და პირობებს",
                        modifier = Modifier.weight(1f),
                        color = textColor
                    )
                    Switch(
                        checked = isAgreed,
                        onCheckedChange = { isAgreed = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = headerColor
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = accentColor,
    unfocusedBorderColor = Color(0xFFCE93D8),
    cursorColor = accentColor
)

@Preview(showBackground = true)
@Composable
fun PreviewFormScreen() {
    Homework2Theme {
        FormScreen()
    }
}
