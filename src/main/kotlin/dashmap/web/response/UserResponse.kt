package dashmap.web.response

import dashmap.entity.member.Member
import dashmap.entity.member.crown.Crown
import dashmap.entity.member.quest.Quest

data class UserResponse(
    val email: String?,
    val name: String?,
    val profileImageUrl: String?,
    val crown: Crown,
    val quest: Quest
) {
    companion object {
        fun of(member: Member): UserResponse {
            return UserResponse(
                member.email,
                member.name,
                member.profileImageUrl,
                Crown(),
                Quest()
            )
        }
    }
}