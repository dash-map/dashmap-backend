package dashmap.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import dashmap.entity.member.Member
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JwtService(
    val USER_ID: String = "userId"
) {
    @Value("\${jwt.issuer}")
    val ISSUER: String? = null

    @Value("\${jwt.secret-key}")
    val SECRET_KEY: String? = null

    fun createKey(user: Member): String {
        return JWT.create()
            .withClaim(USER_ID, user.id)
            .withIssuer(ISSUER)
            .sign(Algorithm.HMAC256(SECRET_KEY))
    }

    fun verifyToken(jwt: String): DecodedJWT {
        val verifier: JWTVerifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
            .withIssuer(ISSUER)
            .build()
        return verifier.verify(jwt)
    }

    fun getUserId(jwt: DecodedJWT): Long = jwt.getClaim(USER_ID).asLong()
}