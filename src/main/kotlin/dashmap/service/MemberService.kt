package dashmap.service

import dashmap.auth.OAuth
import dashmap.auth.dto.AccessTokenResponseDTO
import dashmap.auth.dto.OAuthUserResponseDTO
import dashmap.auth.service.JwtService
import dashmap.entity.member.Member
import dashmap.entity.member.MemberRepository
import dashmap.web.response.AuthUserResponse
import org.springframework.stereotype.Service

@Service
class MemberService(
    val oauth: OAuth,
    val userRepository: MemberRepository,

    val jwtService: JwtService,
    val s3Service: S3Service
) {
    suspend fun login(code: String): AuthUserResponse {
        val token: AccessTokenResponseDTO = oauth.getToken(code)
        println(token)
        val userInfo: OAuthUserResponseDTO = oauth.getUserInfo(token.accessToken)
        println(userInfo)
        val userName = userInfo.login
        println(userName)

        if (verifyUser(userName)) {
            val user = findUserByName(userName)
            val jwtToken = jwtService.createKey(user)
            return AuthUserResponse.of(user, jwtToken)
        }

        val user: Member = Member.of(userInfo)
        userRepository.save(user)
        val jwtToken = jwtService.createKey(user)
        return AuthUserResponse.of(user, jwtToken)
    }

    fun verifyUser(userName: String): Boolean {
        return userRepository.findByName(userName) != null
    }

    fun findUserByName(name: String): Member {
        return userRepository.findByName(name) ?: throw Exception("Can't Found User")
    }
}