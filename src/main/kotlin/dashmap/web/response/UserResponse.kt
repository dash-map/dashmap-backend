package dashmap.web.response

import dashmap.entity.member.Member
import dashmap.entity.crown.Crown
import dashmap.entity.progress.Progress

data class UserResponse(
    val email: String?,
    val name: String?,
    val profileImageUrl: String?,
    val crown: Crown,
    val progress: Progress
) {
    companion object {
        fun of(member: Member): UserResponse {
            return UserResponse(
                member.email,
                member.name,
                member.profileImageUrl,
                Crown(),
                Progress()
            )
        }
    }
}