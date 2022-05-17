package dashmap.entity.crown

import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "crown")
class Crown(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val isFeClear: Boolean = false,
    val isBeClear: Boolean = false,
    val isAosClear: Boolean = false,
    val isIosClear: Boolean = false,
    val isAiClear: Boolean = false,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    val member: Member? = null
) {
}