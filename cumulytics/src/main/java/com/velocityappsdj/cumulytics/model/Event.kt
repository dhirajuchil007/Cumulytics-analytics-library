package com.velocityappsdj.cumulytics.model

import com.velocityappsdj.cumulytics.AnalyticsClient

data class Event(
    val eventName: String,
    val properties: HashMap<String, Any?>? = null,
    val exclude: Set<Class<out AnalyticsClient>>? = null
)
