package cvdevelopers.takehome.dagger.presentation

import cvdevelopers.takehome.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(PresentationModule::class))
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
}