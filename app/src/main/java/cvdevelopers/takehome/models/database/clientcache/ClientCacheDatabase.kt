package cvdevelopers.takehome.models.database.clientcache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cvdevelopers.takehome.models.Client

@Database(entities = [Client::class], version = 1)
@TypeConverters(ClientTypeConverters::class)
abstract class ClientCacheDatabase: RoomDatabase() {

    abstract fun clientCacheDao(): ClientCacheDao
}