package cvdevelopers.takehome

import android.app.Application
import cvdevelopers.takehome.dagger.application.ApplicationComponent
import cvdevelopers.takehome.dagger.application.ApplicationModule
import cvdevelopers.takehome.dagger.application.DaggerApplicationComponent

class LuminaryTakeHomeApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this);
    }

}