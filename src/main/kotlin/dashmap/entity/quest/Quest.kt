package dashmap.entity.quest

import dashmap.entity.question.Question
import javax.persistence.*

@Entity
@Table(name = "quest")
class Quest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    var name: String?,
    var field: String?,
    var problem: String?,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "quest")
    var questions: MutableList<Question>?,
    var answer: Long?
) {
}