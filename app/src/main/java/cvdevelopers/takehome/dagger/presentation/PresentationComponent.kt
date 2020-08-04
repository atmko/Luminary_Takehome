package cvdevelopers.takehome.dagger.presentation

import cvdevelopers.takehome.view.clientlist.ClientListFragment
import cvdevelopers.takehome.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(PresentationModule::class, ViewModelModule::class))
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(clientListFragment: ClientListFragment)
}