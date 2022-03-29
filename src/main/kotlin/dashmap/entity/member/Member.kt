package dashmap.entity.member

import lombok.AccessLevel
import lombok.NoArgsConstructor
import lombok.ToString
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = ["id", "oauthId", "email", "nickname", "introduction", "role"])
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

    var introduction: String?,

    @Enumerated(value = EnumType.STRING)
    var role: Role
) {

}