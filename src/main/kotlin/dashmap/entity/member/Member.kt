package dashmap.entity.member

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
    var oauthId: String,

    @Column(unique = true)
    var email: String?,

    @Column(unique = true)
    var nickname: String,

    var profileImageUrl: String?,

    @Enumerated(value = EnumType.STRING)
    var role: Role
) {

}