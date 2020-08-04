package cvdevelopers.takehome.dagger.presentation

import cvdevelopers.takehome.utils.image.ImageLoader
import cvdevelopers.takehome.view.clientlist.ClientAdapter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideClientAdapter(imageLoader: ImageLoader): ClientAdapter {
        return ClientAdapter(imageLoader)
    }
}