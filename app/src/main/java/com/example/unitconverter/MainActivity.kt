package com.example.unitconverter

import android.graphics.Paint.Style
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()

                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meter") }
    var outputUnit by remember{ mutableStateOf("Meter") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    var conversionFactor = remember{ mutableStateOf(1.00) }
    var oConversionFactor = remember{ mutableStateOf(1.00) }

    fun convertsUnit(){
        val inputDoubleValue = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputDoubleValue * conversionFactor.value / oConversionFactor.value).roundToInt()
        outputValue = result.toString()
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge, color = Color.Red)

        Spacer(modifier = Modifier.height(21.dp))


        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                            inputValue = it
                convertsUnit()
            },
            label = { Text(text = "Enter Value")}
        )
        Spacer(modifier = Modifier.height(21.dp))
        Row {

            Box{
                Button(onClick = { iExpanded = true }) {

                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "centimeter") },
                        onClick = {
                            inputUnit = "centimeter"
                            iExpanded = false
                            conversionFactor.value = 0.01
                            convertsUnit()
                    })
                    DropdownMenuItem(
                        text = { Text(text = "meter") },
                        onClick = {
                            inputUnit = "meter"
                            iExpanded = false
                            conversionFactor.value = 1.0
                            convertsUnit()
                    })
                    DropdownMenuItem(
                        text = { Text(text = "feet") },
                        onClick = {
                            inputUnit = "feet"
                            iExpanded = false
                            conversionFactor.value = 0.3048
                            convertsUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "millimeter") },
                        onClick = {
                            inputUnit = "millimeter"
                            iExpanded = false
                            conversionFactor.value = 0.001
                            convertsUnit()
                    })
                }
            }
            Spacer(modifier = Modifier.width(18.dp))
            Box{
                Button(onClick = { oExpanded = true }) {

                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "centimeter") },
                        onClick = {
                            outputUnit = "centimeter"
                            oExpanded = false
                            oConversionFactor.value = 0.01
                            convertsUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "meter") },
                        onClick = {
                            outputUnit = "meter"
                            oExpanded = false
                            oConversionFactor.value = 1.0
                            convertsUnit()

                        })
                    DropdownMenuItem(
                        text = { Text(text = "feet") },
                        onClick = {
                            outputUnit = "feet"
                            oExpanded = false
                            oConversionFactor.value = 0.3048
                            convertsUnit()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "millimeter") },
                        onClick = {
                            outputUnit = "millimeter"
                            oExpanded = false
                            oConversionFactor.value = 0.001
                            convertsUnit()
                        })
                }
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

        if(outputValue == ""){
            Text("Result:- ", style = MaterialTheme.typography.headlineMedium)
        }else
        Text("Result:- $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
