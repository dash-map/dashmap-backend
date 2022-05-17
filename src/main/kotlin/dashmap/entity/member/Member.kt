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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crown_id")
    var crown: Crown,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    var progress: Progress,
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