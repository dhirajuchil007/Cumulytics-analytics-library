package com.velocityappsdj.cumulytics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.velocityappsdj.cumulytics.model.Event
import com.velocityappsdj.cumulytics_clevertap.ClevertapClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        MyApp.cumulyticsInstance.setUser(
            "dhiraj", hashMapOf(
                "name" to "Dhiraj",
                "age" to "25"
            )
        )
        MyApp.cumulyticsInstance.sendEvent(Event("user_login"))

        MyApp.cumulyticsInstance.sendEvent(
            Event(
                "user_info_loaded", hashMapOf(
                    "os" to "android",
                    "version" to "1.0"
                )
            )
        )

        MyApp.cumulyticsInstance.sendEvent(
            Event(
                eventName = "user_info_displayed", exclude = setOf(
                    ClevertapClient::class.java
                )
            )
        )
    }
}