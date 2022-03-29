package dashmap.entity.member

import lombok.Getter
import lombok.RequiredArgsConstructor

@Getter
@RequiredArgsConstructor
enum class Role(val key: String) {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN")
}