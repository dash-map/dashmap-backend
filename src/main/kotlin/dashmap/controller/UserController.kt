package dashmap.controller

import dashmap.service.MemberService
import dashmap.web.response.AuthUserResponse
import dashmap.web.response.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
    val memberService: MemberService
) {

    @GetMapping("/user/{userId}")
    fun userInfo(@PathVariable("userId") userId: Long): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(memberService.findUserById(userId))
    }

    // GET https://github.com/login/oauth/authorize?client_id=e478d9cea0fa33472386
    @GetMapping("/login")
    suspend fun login(@RequestParam code: String): ResponseEntity<AuthUserResponse> {
        return ResponseEntity.ok(memberService.login(code))
    }
}