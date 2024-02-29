package com.geniusapk.converteasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.geniusapk.converteasy.ui.theme.ConvertEasyTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            ConvertEasyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()

                }


            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oconversionFactor = remember { mutableDoubleStateOf(1.00) }


    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor.doubleValue * 100.0 / oconversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Convert Easy",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold


        )
        Specer(32.dp)


        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()

            },
            label = { Text(text = "Enter Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Specer(wdp = 8.dp)


        Row {
            //input box
            Box() {
                //input button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.graphicsLayer(rotationZ = if (iExpanded) 180f else 0f)
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Centimeters"
                        conversionFactor.doubleValue = 0.01
                        convertUnits()

                    })

                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded = false
                        inputUnit = "Meters"
                        conversionFactor.doubleValue = 1.0
                        convertUnits()

                    })

                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded = false
                        inputUnit = "Feet"
                        conversionFactor.doubleValue = 0.3048
                        convertUnits()

                    })

                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = {
                        iExpanded = false
                        inputUnit = "Milimeter"
                        conversionFactor.doubleValue = 0.001
                        convertUnits()


                    })

                }


            }

            Specer(16.dp)

            //outpute box
            Box() {
                //output button
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.graphicsLayer(rotationZ = if (oExpanded) 180f else 0f)
                    )

                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Centimeters"
                        oconversionFactor.doubleValue = 0.01
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded = false
                        outputUnit = "Meters"
                        oconversionFactor.doubleValue = 1.00
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputUnit = "Feet"
                        oconversionFactor.doubleValue = 0.3048
                        convertUnits()

                    })

                    DropdownMenuItem(text = { Text(text = "Milimeter") }, onClick = {

                        oExpanded = false
                        outputUnit = "Milimeter"
                        oconversionFactor.doubleValue = 0.001
                        convertUnits()

                    })


                }


            }


        }

        Specer(wdp = 4.dp)

        Text(
            text = "Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall,

        )
    }
}


@Composable
fun Specer(wdp: Dp) {
    Spacer(modifier = Modifier.padding(wdp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Pre() {
    ConvertEasyTheme {
        UnitConverter()

    }
}
