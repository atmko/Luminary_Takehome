package cvdevelopers.takehome.dagger.presentation;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import cvdevelopers.takehome.utils.executors.AppExecutors;
import cvdevelopers.takehome.api.RandomUserApiEndpoint;
import cvdevelopers.takehome.models.database.clientcache.ClientCacheDao;
import cvdevelopers.takehome.viewmodel.ClientListViewModel;
import cvdevelopers.takehome.viewmodel.ViewModelFactory;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory provideViewModelFactory
            (Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(ClientListViewModel.class)
    ViewModel provideClientListViewModel(ClientCacheDao clientCacheDao,
                                         AppExecutors appExecutors,
                                         RandomUserApiEndpoint randomUserApiEndpoint) {
        return new ClientListViewModel(
                clientCacheDao,
                appExecutors,
                randomUserApiEndpoint);
    }
}