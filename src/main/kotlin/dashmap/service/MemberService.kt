package dashmap.service

import dashmap.auth.OAuth
import dashmap.auth.dto.AccessTokenResponseDTO
import dashmap.auth.dto.OAuthUserResponseDTO
import dashmap.auth.service.JwtService
import dashmap.entity.crown.Crown
import dashmap.entity.crown.CrownRepository
import dashmap.entity.member.Member
import dashmap.entity.member.MemberRepository
import dashmap.entity.progress.Progress
import dashmap.entity.progress.ProgressRepository
import dashmap.web.response.AuthUserResponse
import dashmap.web.response.UserResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MemberService(
    val oauth: OAuth,
    val userRepository: MemberRepository,
    val progressRepository: ProgressRepository,
    val crownRepository: CrownRepository,

    val jwtService: JwtService,
) {

    @Transactional
    fun findUserById(userId: Long): UserResponse {
        val user: Member? = userRepository.findByIdOrNull(userId)
        user?.let {
            val pro = progressRepository.findByMember(user)
            val crown = crownRepository.findByMember(user)
            return UserResponse.of(it, pro, crown)
        } ?: throw Exception("User is not Exist")
    }

    @Transactional
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
            return AuthUserResponse.of(user, jwtToken, token.accessToken)
        }

        val user: Member = Member.of(userInfo)
        userRepository.save(user)
        progressRepository.save(Progress.of(user))
        crownRepository.save(Crown.of(user))
        val jwtToken = jwtService.createKey(user)
        return AuthUserResponse.of(user, jwtToken, token.accessToken)
    }

    fun verifyUser(userName: String): Boolean {
        return userRepository.findByName(userName) != null
    }

    fun findUserByName(name: String): Member {
        return userRepository.findByName(name) ?: throw Exception("User is not Exist")
    }
}