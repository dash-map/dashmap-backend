package dashmap.entity.quest

import dashmap.entity.member.problem.Problem
import javax.persistence.*

@Entity
@Table(name = "quest")
class Quest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "quest")
    val problems: List<Problem>?,
    val answer: Long?
) {
}