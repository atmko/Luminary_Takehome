package cvdevelopers.takehome.view.common

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.dagger.application.ApplicationComponent
import cvdevelopers.takehome.dagger.presentation.PresentationComponent
import cvdevelopers.takehome.dagger.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        return getApplicationComponent()
                    .newPresentationComponent(PresentationModule())
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (application as LuminaryTakeHomeApplication).appComponent
    }
}