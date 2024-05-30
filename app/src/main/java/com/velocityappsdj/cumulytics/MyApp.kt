package com.velocityappsdj.cumulytics

import android.app.Application
import com.velocityappsdj.cumulytics_clevertap.ClevertapClient
import com.velocityappsdj.cumulytics_logging_interceptor.LoggingInterceptor

class MyApp : Application() {
    companion object {
        lateinit var cumulyticsInstance: CumulyticsInstance
    }

    override fun onCreate() {

        cumulyticsInstance = CumulyticsInstance
            .Builder()
            .addClient(
                ClevertapClient(
                    this, "YOUR-ACCOUNT-ID", "YOUR-TOKEN"
                )
            )
            .addClient(CustomAnalyticsClient())
            .addInterceptor(LoggingInterceptor())
            .build()
        cumulyticsInstance.init(this)

        super.onCreate()

    }

}