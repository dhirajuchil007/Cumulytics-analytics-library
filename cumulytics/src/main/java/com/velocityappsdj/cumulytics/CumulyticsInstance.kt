package com.velocityappsdj.cumulytics

import android.content.Context
import com.velocityappsdj.cumulytics.interceptor.EventInterceptor
import com.velocityappsdj.cumulytics.model.Event

class CumulyticsInstance private constructor(
    private val clients: List<AnalyticsClient>,
    private val interceptors: List<EventInterceptor>
) :
    Cumulytics {

    class Builder {

        private val clients = mutableListOf<AnalyticsClient>()
        private val interceptors = mutableListOf<EventInterceptor>()

        fun addClient(analyticsClient: AnalyticsClient) = apply { clients.add(analyticsClient) }

        fun addInterceptor(eventInterceptor: EventInterceptor) =
            apply { interceptors.add(eventInterceptor) }

        fun build(): CumulyticsInstance {
            return CumulyticsInstance(clients, interceptors)
        }

    }

    override fun init(context: Context) {
        clients.forEach {
            it.init(context)
        }
    }

    override fun setUser(id: String, params: HashMap<String, String>) {
        clients.filterIsInstance<UserProperties>().forEach {
            it.setUser(userId = id, params = params)
        }
    }

    override fun setUserParams(params: HashMap<String, String>) {
        clients.filterIsInstance<UserProperties>().forEach {
            it.setUserParams(params = params)
        }
    }

    override fun sendEvent(
        event: Event
    ) {
        applyInterceptors(event) { interceptedEvent ->
            filterExcludedClients(interceptedEvent.exclude).forEach {
                it.sendEvent(interceptedEvent)
            }
        }
    }


    override fun sendAll(exclude: Set<Class<out AnalyticsClient>>?) {
        filterExcludedClients(exclude).forEach {
            it.sendAll()
        }
    }

    private fun filterExcludedClients(exclude: Set<Class<out AnalyticsClient>>?) =
        exclude?.let { excludedClient ->
            clients.filter { excludedClient.contains(it.javaClass).not() }
        } ?: clients

    private fun applyInterceptors(event: Event, block: (interceptedEvent: Event) -> Unit) {
        var modifiedEvent = event.copy()
        var cancelled = false
        interceptors.forEach {
            modifiedEvent = it.intercept(event) {
                cancelled = true
            }
        }
        if (cancelled.not())
            block(modifiedEvent)
    }
}