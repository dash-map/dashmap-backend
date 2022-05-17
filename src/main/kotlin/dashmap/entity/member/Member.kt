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
    val id: Long? = null,

    @Column(unique = true)
    val email: String?,

    @Column(unique = true)
    val name: String?,
    val profileImageUrl: String?,

    @Enumerated(value = EnumType.STRING)
    val role: Role,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crown_id")
    val crown: Crown,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    val progress: Progress,
) {
    companion object {
        fun of(user: OAuthUserResponseDTO, crown: Crown, progress: Progress): Member {
            return Member(
                null,
                user.email,
                user.login,
                user.avatar_url,
                Role.USER,
                crown,
                progress
            )
        }
    }
}