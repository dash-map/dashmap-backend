package dashmap.entity.progress

import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "progress")
class Progress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val feCount: Int = 0,
    val beCount: Int = 0,
    val aosCount: Int = 0,
    val iosCount: Int = 0,
    val aiCount: Int = 0,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    val member: Member? = null
) {

}