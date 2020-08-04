package cvdevelopers.takehome.dagger.application

import android.app.Application
import androidx.room.Room
import cvdevelopers.takehome.utils.executors.AppExecutors
import cvdevelopers.takehome.models.database.clientcache.ClientCacheDao
import cvdevelopers.takehome.models.database.clientcache.ClientCacheDatabase
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DatabaseModule  {

    @Provides
    @Singleton
    fun provideClientCacheDao(database: ClientCacheDatabase): ClientCacheDao {
        return database.clientCacheDao()
    }

    @Provides
    @Singleton
    fun provideClientCacheDatabase(application: Application): ClientCacheDatabase {
        return Room.databaseBuilder(
                application,
                ClientCacheDatabase::class.java,
                "client_cache_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppExecutors(singleThreadExecutor: ExecutorService,
                            mainThreadExecutor: Executor): AppExecutors {
        return AppExecutors(singleThreadExecutor, mainThreadExecutor)
    }

    @Provides
    @Singleton
    fun provideSingleThreadExecutor(): ExecutorService {
        return Executors.newSingleThreadExecutor()!!
    }

    @Provides
    @Singleton
    fun provideMainThreadExecutor(application: Application): Executor {
        return application.mainExecutor
    }
}
