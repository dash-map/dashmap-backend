package dashmap.entity.progress

import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "progress")
class Progress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var feCount: Int = 0,
    var beCount: Int = 0,
    var aosCount: Int = 0,
    var iosCount: Int = 0,
    var aiCount: Int = 0,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    var member: Member? = null
) {

}