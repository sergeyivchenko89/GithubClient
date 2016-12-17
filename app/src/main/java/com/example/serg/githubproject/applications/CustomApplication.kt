package com.example.serg.githubproject.applications

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * @author Sergey Ivchenko <sergey.ivchenko89@yandex.ru>
 */
class CustomApplication() : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.
                        newInitializerBuilder(this).
                        enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).
                        enableDumpapp(Stetho.defaultDumperPluginsProvider(this)).
                        build()
        )
    }

}