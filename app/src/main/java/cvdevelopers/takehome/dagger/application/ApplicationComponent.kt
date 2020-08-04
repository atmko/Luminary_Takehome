package cvdevelopers.takehome.dagger.application

import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.dagger.presentation.PresentationComponent
import cvdevelopers.takehome.dagger.presentation.PresentationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CamiloVega on 10/7/17.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkClientModule::class,
        DatabaseModule::class)
)
interface ApplicationComponent {
    fun inject(app: LuminaryTakeHomeApplication)

    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}