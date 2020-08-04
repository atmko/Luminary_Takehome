package cvdevelopers.takehome.models.database.clientcache

import androidx.room.TypeConverter
import cvdevelopers.takehome.models.Id
import cvdevelopers.takehome.models.Name
import cvdevelopers.takehome.models.Picture
import java.lang.StringBuilder

class ClientTypeConverters {

    @TypeConverter
    fun idToString(id: Id): String {
        return StringBuilder()
                .append(id.name)
                .append(" ")
                .append(id.value)
                .toString()
    }

    @TypeConverter
    fun stringToId(idString: String): Id {
        val idArgs: List<String> = idString.split(" ")
        return Id(idArgs[0], idArgs[1])
    }

    @TypeConverter
    fun nameToString(name: Name): String {
        return StringBuilder()
                .append(name.first)
                .append(" ")
                .append(name.last)
                .append(" ")
                .append(name.title)
                .toString()
    }

    @TypeConverter
    fun stringToName(nameString: String): Name {
        val nameArgs: List<String> = nameString.split(" ")
        return Name(nameArgs[0], nameArgs[1], nameArgs[2])
    }

    @TypeConverter
    fun pictureToString(picture: Picture): String {
        return StringBuilder()
                .append(picture.large)
                .append(" ")
                .append(picture.medium)
                .append(" ")
                .append(picture.thumbnail)
                .toString()
    }

    @TypeConverter
    fun stringToPicture(pictureString: String): Picture {
        val pictureArgs: List<String> = pictureString.split(" ")
        return Picture(pictureArgs[0], pictureArgs[1], pictureArgs[2])
    }
}