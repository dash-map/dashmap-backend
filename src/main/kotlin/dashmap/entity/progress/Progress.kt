package dashmap.entity.progress

import com.fasterxml.jackson.annotation.JsonProperty
import dashmap.entity.member.Member
import javax.persistence.*

@Entity
@Table(name = "progress")
class Progress(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var feCount: Int,
    var beCount: Int,
    var aosCount: Int,
    var iosCount: Int,
    var aiCount: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var member: Member
) {
    companion object {
        fun of(member: Member): Progress {
            return Progress(
                null,
                0,
                0,
                0,
                0,
                0,
                member
            )
        }
    }
}