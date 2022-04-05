package dashmap.auth

import dashmap.auth.annotation.LoginRequired
import dashmap.auth.service.JwtService
import dashmap.entity.member.MemberRepository
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthInterceptor(
    val AUTHORIZATION: String = "Authorization",
    val BEARER: String = "Bearer",
    val USER_ID: String = "userId",
    val memberRepository: MemberRepository,
    val jwtService: JwtService
): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (loginRequired(handler)) {
            verifyJwt(request)
            verifyUser(request)
        }

        return true
    }

    fun loginRequired(handler: Any): Boolean {
        return handler is HandlerMethod && handler.hasMethodAnnotation(LoginRequired::class.java)
    }

    private fun verifyUser(request: HttpServletRequest) {
        val userId: Any = request.getAttribute(USER_ID)
        memberRepository?.findById(userId as Long)
    }

    private fun verifyJwt(request: HttpServletRequest) {
        val header: String = request.getHeader(AUTHORIZATION)
        verifyHeader(header)

        val jwt = header.substring(BEARER.length).trim()
        val decodedJWT = jwtService.verifyToken(jwt)

        request.setAttribute(USER_ID, jwtService.getUserId(decodedJWT))
    }

    private fun verifyHeader(header: String) {
        if (!header.startsWith(BEARER)) {
            throw Exception("HeaderFormatException.")
        }
    }
}