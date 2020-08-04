package cvdevelopers.takehome.dagger.presentation

import cvdevelopers.takehome.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(PresentationModule::class))
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}