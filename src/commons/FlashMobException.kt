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


/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 22/12/17
 */

class FlashMobException(val code: Int): Exception() {
    override lateinit var message: String

    init {
        message = when(code){
            Code.BAD_REQUEST -> "request couldn't be parsed"
            Code.UNAUTHORIZED -> "Unauthorized access"
            Code.NOT_FOUND -> "record not found"
            Code.CONFLICT -> "resource already exist"
            else -> "generic error"
        }
    }
}

object Code{
    val GET_OK = 200
    val POST_OK = 201
    val PUT_OK = 202
    val DELETE_OK = 204
    val BAD_REQUEST = 400
    val UNAUTHORIZED = 401
    val NOT_FOUND = 404
    val CONFLICT = 409
}