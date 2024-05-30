package com.velocityappsdj.cumulytics_clevertap

import android.app.Application
import android.content.Context
import android.util.Log
import com.clevertap.android.sdk.ActivityLifecycleCallback
import com.clevertap.android.sdk.CleverTapAPI
import com.velocityappsdj.cumulytics.AnalyticsClient
import com.velocityappsdj.cumulytics.UserProperties
import com.velocityappsdj.cumulytics.model.Event


class ClevertapClient(
    val application: Application,
    val clevertapAccountId: String,
    val clevertapToken: String
) : AnalyticsClient, UserProperties {

    companion object {
        private const val TAG = "ClevertapClient"
    }

    override fun init(context: Context) {
        ActivityLifecycleCallback.register(application)
        CleverTapAPI.changeCredentials(clevertapAccountId, clevertapToken)
    }

    override fun sendEvent(event: Event) {
        Log.d(TAG, "sendEvent() called with: event = $event")

        CleverTapAPI.getDefaultInstance(application.applicationContext)
            ?.pushEvent(event.eventName, event.properties)
    }

    override fun sendAll() {
        CleverTapAPI.getDefaultInstance(application.applicationContext)?.flush()
    }

    override fun setUser(userId: String, params: HashMap<String, String>?) {
        CleverTapAPI.getDefaultInstance(application.applicationContext)?.let {
            val paramsNew = params ?: hashMapOf()
            paramsNew["user_id"] = userId
            it.onUserLogin(paramsNew.toMap())
        }
    }

    override fun setUserParams(params: HashMap<String, String>) {
        CleverTapAPI.getDefaultInstance(application.applicationContext)?.let {
            it.pushProfile(params.toMap())
        }
    }
}