package dashmap.entity.member

import dashmap.auth.dto.OAuthUserResponseDTO
import dashmap.entity.crown.Crown
import dashmap.entity.progress.Progress
import lombok.ToString
import javax.persistence.*

@Entity
@Table(name = "member")
@ToString(of = ["id", "oauthId", "email", "nickname", "role"])
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var email: String?,

    @Column(unique = true)
    var name: String?,
    var profileImageUrl: String?,

    @Enumerated(value = EnumType.STRING)
    var role: Role,
) {
    companion object {
        fun of(user: OAuthUserResponseDTO): Member {
            return Member(
                null,
                user.email,
                user.login,
                user.avatar_url,
                Role.USER,
            )
        }
    }
}