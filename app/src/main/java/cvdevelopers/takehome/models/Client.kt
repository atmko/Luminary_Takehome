package cvdevelopers.takehome.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "client_cache")
class Client @Ignore constructor(
        val email: String,
        val id: Id,
        val name: Name,
        val picture: Picture) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cache_id")
    var mCacheId: Int = 0

    constructor(cacheId: Int,
                email: String,
                id: Id,
                name: Name,
                picture: Picture) : this(email, id, name, picture) {
        this.mCacheId = cacheId
    }
}