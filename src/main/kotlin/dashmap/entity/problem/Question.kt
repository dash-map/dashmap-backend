package dashmap.entity.problem

import com.fasterxml.jackson.annotation.JsonIgnore
import dashmap.entity.quest.Quest
import javax.persistence.*

@Entity
@Table(name = "question")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val question: String?,

    @ManyToOne
    @JoinColumn(name = "quest_id")
    @JsonIgnore
    val quest: Quest?
) {
}