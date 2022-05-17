package dashmap.web

import dashmap.auth.annotation.LoginRequired
import dashmap.service.MemberService
import dashmap.web.response.AuthUserResponse
import dashmap.web.response.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MemberController(
    val memberService: MemberService
) {

    //@LoginRequired
    @GetMapping("/user/{userId}")
    fun userInfo(@PathVariable("userId") userId: Long): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(memberService.findUserById(userId))
    }

    // GET https://github.com/login/oauth/authorize?client_id=efc5f3748a002ebb37cd
    @GetMapping("/login")
    suspend fun login(@RequestParam code: String): ResponseEntity<AuthUserResponse> {
        return ResponseEntity.ok(memberService.login(code))
    }
}