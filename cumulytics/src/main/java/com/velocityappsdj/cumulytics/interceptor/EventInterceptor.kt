package com.velocityappsdj.cumulytics.interceptor

import com.velocityappsdj.cumulytics.model.Event

interface EventInterceptor {
    fun intercept(event: Event, cancelEvent: () -> Unit): Event
}