package yixin12162521.ch.com.example.accelerometer2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import android.content.Context.SENSOR_SERVICE
import android.hardware.SensorManager
import android.hardware.Sensor

@Composable
fun FirstSreen() {
    var msg by remember { mutableStateOf("加速感應器實例")}
    var msg2 by remember { mutableStateOf("") }
    var xTilt by remember { mutableStateOf(0f) }
    var yTilt by remember { mutableStateOf(0f) }
    var zTilt by remember { mutableStateOf(0f) }

    val context = LocalContext.current
    val sensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                xTilt = event.values[0]
                yTilt = event.values[1]
                zTilt = event.values[2]
                msg = "加速感應器實例\n" + String.format(
                    "x軸: %1.2f \n"
                            + "y軸: %1.2f \n" +
                            "z軸: %1.2f", xTilt, yTilt, zTilt
                )
            }
        }
        if (Math.abs(xTilt)<1 && Math.abs(xTilt)<1 && zTilt<-9)
        {
            msg2 = "朝下平放"
        }
        else if (Math.abs(xTilt) + Math.abs(yTilt) + Math.abs(zTilt) > 32)
        {
            msg2 = "手機搖晃"
        }


        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

        }

    }

    Column (modifier = Modifier.fillMaxSize().background(Color.Green),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(msg)
        Text(msg2)
        Button (onClick = { showSecond = true})
        {
            Text(text = "跳轉畫面2")
        }
    }

    if (showSecond){
        SecondScreen()
    }

}
