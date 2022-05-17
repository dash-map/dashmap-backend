package dashmap.entity.crown

import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "crown")
class Crown(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var isFeClear: Boolean = false,
    var isBeClear: Boolean = false,
    var isAosClear: Boolean = false,
    var isIosClear: Boolean = false,
    var isAiClear: Boolean = false,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    var member: Member? = null
) {
}