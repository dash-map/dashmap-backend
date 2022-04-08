package dashmap.entity.member

import dashmap.auth.dto.OAuthUserResponseDTO
import lombok.AccessLevel
import lombok.NoArgsConstructor
import lombok.ToString
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    var role: Role = Role.USER
) {
    companion object {
        fun of(user: OAuthUserResponseDTO): Member {
            return Member(
                null,
                user.email,
                user.login,
                user.avatar_url
            )
        }
    }
}