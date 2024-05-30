package com.velocityappsdj.cumulytics

import android.content.Context
import com.velocityappsdj.cumulytics.model.Event

class CustomAnalyticsClient : AnalyticsClient, UserProperties {
    override fun init(context: Context) {
        //do stuff
    }

    override fun sendEvent(event: Event) {
        //do stuff
    }

    override fun sendAll() {
        //do stuff
    }

    override fun setUser(userId: String, params: HashMap<String, String>?) {
        //do stuff
    }

    override fun setUserParams(params: HashMap<String, String>) {
        //do stuff
    }
}