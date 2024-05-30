package com.velocityappsdj.cumulytics_logging_interceptor

import android.util.Log
import com.velocityappsdj.cumulytics.interceptor.EventInterceptor
import com.velocityappsdj.cumulytics.model.Event

class LoggingInterceptor : EventInterceptor {
    companion object {
        private const val TAG = "CumulyticsLogging"
    }

    override fun intercept(event: Event, cancelEvent: () -> Unit): Event {
        Log.d(TAG, "Sending Event $event")
        return event
    }
}