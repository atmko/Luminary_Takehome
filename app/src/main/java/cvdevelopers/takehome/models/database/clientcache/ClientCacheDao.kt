package cvdevelopers.takehome.models.database.clientcache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cvdevelopers.takehome.models.Client
import io.reactivex.Single

@Dao
interface ClientCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClient(client: Client)

    @Query("SELECT * FROM client_cache ORDER BY cache_id")
    fun getAllClients(): Single<List<Client>>

    @Query("DELETE FROM client_cache")
    fun deleteCache()
}