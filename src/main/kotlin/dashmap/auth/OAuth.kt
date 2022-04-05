package dashmap.auth

import dashmap.auth.dto.AccessTokenResponseDTO
import dashmap.auth.dto.OAuthUserResponseDTO

interface OAuth {
    suspend fun getToken(code: String): AccessTokenResponseDTO

    suspend fun getUserInfo(accessToken: String): OAuthUserResponseDTO
}