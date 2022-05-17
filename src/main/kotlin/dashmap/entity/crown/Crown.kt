package dashmap.entity.crown

import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "crown")
class Crown(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var isFeClear: Boolean,
    var isBeClear: Boolean,
    var isAosClear: Boolean,
    var isIosClear: Boolean,
    var isAiClear: Boolean,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member?
) {
    companion object {
        fun of(member: Member): Crown {
            return Crown(
                null,
                false,
                false,
                false,
                false,
                false,
                member
            )
        }
    }
}