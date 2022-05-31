package dashmap.entity.question

import dashmap.entity.quest.Quest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : CrudRepository<Question, Long> {
    fun findAllByQuest(quest: Quest): List<Question>
}