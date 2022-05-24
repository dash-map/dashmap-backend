package dashmap.web

import dashmap.auth.annotation.LoginRequired
import dashmap.service.QuestService
import dashmap.web.request.QuestRequest
import dashmap.web.response.QuestCountResponse
import dashmap.web.response.QuestResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class QuestController(
    val questService: QuestService
) {
    @PostMapping("/quest")
    fun questInfo(@RequestBody request: QuestRequest): ResponseEntity<QuestResponse> {
        return ResponseEntity.ok(questService.findQuestById(request))
    }

    @GetMapping("/quests")
    fun questCount(): ResponseEntity<QuestCountResponse> {
        return ResponseEntity.ok(questService.findQuestCountByField())
    }
}