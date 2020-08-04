package cvdevelopers.takehome.view.common

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.dagger.application.ApplicationComponent
import cvdevelopers.takehome.dagger.presentation.PresentationComponent
import cvdevelopers.takehome.dagger.presentation.PresentationModule

open class BaseFragment : Fragment() {

    private var isInjected: Boolean = false

    @UiThread
    protected fun getPresentationComponent(): PresentationComponent {
        if (!isInjected) {
            isInjected = true
            return getApplicationComponent()
                    .newPresentationComponent(PresentationModule())
        }

        throw RuntimeException("getPresentationComponent() called more than once")
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (activity!!.application as LuminaryTakeHomeApplication).appComponent
    }
}