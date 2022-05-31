package dashmap.entity.question

import com.fasterxml.jackson.annotation.JsonIgnore
import dashmap.entity.quest.Quest
import javax.persistence.*

@Entity
@Table(name = "question")
class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var question: String?,

    @ManyToOne
    @JoinColumn(name = "quest_id")
    @JsonIgnore
    var quest: Quest?
) {
}