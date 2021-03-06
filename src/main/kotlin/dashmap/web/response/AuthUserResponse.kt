package dashmap.web.response

import dashmap.entity.member.Member

data class AuthUserResponse(
    val userId: Long?,
    val email: String?,
    val name: String?,
    val profileImageUrl: String?,
    val jwt: String,
    val access_token: String
) {
    companion object {
        fun of(member: Member, token: String, access_token: String): AuthUserResponse {
            return AuthUserResponse(
                member.id,
                member.email,
                member.name,
                member.profileImageUrl,
                token,
                access_token
            )
        }
    }
}