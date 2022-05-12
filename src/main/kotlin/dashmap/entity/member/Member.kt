package dashmap.entity.member

import dashmap.auth.dto.OAuthUserResponseDTO
import dashmap.entity.member.crown.Crown
import dashmap.entity.member.progress.Progress
import lombok.AccessLevel
import lombok.NoArgsConstructor
import lombok.ToString
import javax.persistence.*

@Entity
@Table(name = "member")
@ToString(of = ["id", "oauthId", "email", "nickname", "role"])
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val email: String?,

    @Column(unique = true)
    val name: String?,
    val profileImageUrl: String?,

    @Enumerated(value = EnumType.STRING)
    val role: Role = Role.USER,

    @Transient
    val crown: Crown = Crown(),

    @Transient
    val progress: Progress = Progress()
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