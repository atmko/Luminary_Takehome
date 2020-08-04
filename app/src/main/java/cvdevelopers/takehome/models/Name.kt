package cvdevelopers.takehome.models

import java.lang.StringBuilder

data class Name(
        val first: String,
        val last: String,
        val title: String
) {
    fun getFullNme(): String {
        return StringBuilder()
                .append(first)
                .append(" ")
                .append(last)
                .toString()
    }
}