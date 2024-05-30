<h2>Cumulytics - The analytics aggregation library</h2>

<p>This library can initialize and send events to multiple analytics frameworks in your app. 
  <br>Clients supported out of the box: Clevertap
  <h3>Features</h3>
<ul>
  <li>Supports clever tap out of box</li>
  <li> Ability to add interceptors</li>
  <li>Ability to create custom clients for any other frameworks like mixpanel, blaze etc.</li>
  <li>Library is modular with ability to add only required dependecies</li>
</ul>

    ```
    implementation(project(":cumulytics"))

    //optional clients
    implementation(project(":cumulytics-clevertap"))

    //logging-interceptor
    implementation(project(":cumulytics-logging-interceptor"))

```
</p>

<p>
  You can initialize it in your app's application class
  
  ```

class MyApp : Application() {
companion object {
lateinit var cumulyticsInstance: CumulyticsInstance
}

    override fun onCreate() {

        cumulyticsInstance =
            CumulyticsInstance.Builder().addClient(
                ClevertapClient(
                    this,
                    "", ""
                )
            ).addInterceptor(LoggingInterceptor())

                .build()
        cumulyticsInstance.init(this)

        super.onCreate()

    }

}

  ```

Then register the user
```

MyApp.cumulyticsInstance.setUser(
"dhiraj", hashMapOf(
"name" to "Dhiraj",
"age" to "25"
)
)

```

You can send event with event name, params(optional) also choose not to send particular events to certain frameworks
```

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

```

You can create custom analytics client
```

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

```

Then add it while building your CumulyticsInstance
```

cumulyticsInstance = CumulyticsInstance
    .Builder()
    .addClient(
        ClevertapClient(
        this, "", ""
        )
    )
    .addClient(CustomAnalyticsClient())
    .addInterceptor(LoggingInterceptor())
    .build()

```
</p>
