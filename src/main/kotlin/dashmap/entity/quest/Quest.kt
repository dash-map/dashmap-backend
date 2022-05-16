package dashmap.entity.quest

import dashmap.entity.problem.Question
import dashmap.web.response.QuestResponse
import javax.persistence.*

@Entity
@Table(name = "quest")
class Quest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String?,
    val field: String?,
    val problem: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "quest")
    val questions: List<Question>?,
    val answer: Long?
) {
}