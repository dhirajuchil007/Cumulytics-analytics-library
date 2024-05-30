package com.velocityappsdj.cumulytics

import android.content.Context
import com.velocityappsdj.cumulytics.model.Event

interface Cumulytics {
    fun init(context: Context)

    fun setUser(id: String, params: HashMap<String, String>)

    fun setUserParams(params: HashMap<String, String>)

    fun sendEvent(
        event: Event
    )

    fun sendAll(exclude: Set<Class<out AnalyticsClient>>? = null)

}