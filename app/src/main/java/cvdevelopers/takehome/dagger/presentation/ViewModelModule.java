package cvdevelopers.takehome.dagger.presentation;

import androidx.lifecycle.ViewModel;

import java.util.Map;

import javax.inject.Provider;

import cvdevelopers.takehome.viewmodel.ViewModelFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    ViewModelFactory provideViewModelFactory
            (Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }
}