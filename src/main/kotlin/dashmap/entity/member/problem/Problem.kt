package dashmap.entity.member.problem

import dashmap.entity.quest.Quest
import javax.persistence.*

@Entity
@Table(name = "problem")
class Problem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val question: String?,

    @ManyToOne
    @JoinColumn(name = "quest_id")
    val quest: Quest?
) {
}