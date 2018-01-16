package commons

import java.io.Serializable
import java.util.*


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 22/12/17
 */

data class FlashMob (var name: String, var start: Date, var end: Date,
                     var description: String=""): Serializable, Comparable<FlashMob> {

    override fun compareTo(other: FlashMob): Int {
        var compare = start.compareTo(other.start)
        if (compare==0)
            compare = end.compareTo(other.end)
        if(compare==0)
            compare = name.compareTo(other.name)
        return compare
    }
}