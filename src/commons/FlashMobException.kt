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