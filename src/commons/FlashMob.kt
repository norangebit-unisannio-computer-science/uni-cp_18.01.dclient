/*
 *       copyright (c)  2018 Raffaele Mignone
 *
 *        This file is part of D Client
 *
 *        D Client is free software: you can redistribute it and/or modify
 *        it under the terms of the GNU General Public License as published by
 *        the Free Software Foundation, either version 3 of the License, or
 *        (at your option) any later version.
 *
 *        D Client is distributed in the hope that it will be useful,
 *        but WITHOUT ANY WARRANTY; without even the implied warranty of
 *        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *        GNU General Public License for more details.
 *
 *        You should have received a copy of the GNU General Public License
 *        along with D Client.  If not, see <http://www.gnu.org/licenses/>.
 */

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