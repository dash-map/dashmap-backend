package dashmap.auth

import dashmap.auth.dto.AccessTokenRequestDTO
import dashmap.auth.dto.AccessTokenResponseDTO
import dashmap.auth.dto.OAuthUserResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class GithubOAuth(
    val webClient: WebClient,
): OAuth {
    @Value("\${github.access-token-uri}")
    private val accessTokenUri: String? = null

    @Value("\${github.user-uri}")
    private val userUri: String? = null

    @Value("\${github.client-id}")
    private val clientId: String? = null

    @Value("\${github.client-secret}")
    private val clientSecret: String? = null

    override suspend fun getToken(code: String): AccessTokenResponseDTO {
        val accessTokenRequest = AccessTokenRequestDTO(
            clientId.toString(),
            clientSecret.toString(),
            code
        )
        println("AccessTokenRequest" + accessTokenRequest)
//        val url = accessTokenUri + "?client_id=" + accessTokenRequest.clientId + "&client_secret=" +
//                accessTokenRequest.clientSecret + "&code=" + accessTokenRequest.code
        return webClient.post()
            .uri(accessTokenUri.toString())
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequest)
            .retrieve()
            .awaitBody()
    }

    override suspend fun getUserInfo(accessToken: String): OAuthUserResponseDTO {
        println("access token: " + accessToken)
        return webClient.get()
            .uri(userUri.toString())
            .accept(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "token $accessToken")
            .retrieve()
            .awaitBody()
    }
}
