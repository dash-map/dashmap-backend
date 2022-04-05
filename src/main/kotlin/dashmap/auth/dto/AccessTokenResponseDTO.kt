package dashmap.auth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import lombok.ToString

@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class AccessTokenResponseDTO(
    private val accessToken: String,
    private val tokenType: String,
    private val scope: String
) {
}