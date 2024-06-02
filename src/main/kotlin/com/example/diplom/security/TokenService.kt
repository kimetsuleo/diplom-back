package com.example.diplom.security

/**
 * @author kimetsu - 02.06.2024 - 14:44
 */

import com.example.diplom.properties.SecurityProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.*

/**
 * @author kimetsu - 05.01.2024 - 21:04
 */
@Component
class TokenService(
    val properties: SecurityProperties
) {
    private fun extractAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(key())
            .build()

        return parser.parseSignedClaims(token).payload
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token) { claims -> claims.subject }
    }

    private fun generateTokenFromClaims(
        claims: Claims,
        tokenValidity: Long
    ): String {
        val now = Date()

        return Jwts.builder()
            .claims(claims)
            .issuer("diplom")
            .issuedAt(now)
            .expiration(Date(now.time + tokenValidity))
            .signWith(key())
            .compact()
    }

    fun generateToken(
        username: String,
    ): String {
        val claims = Jwts.claims()
            .subject(username)
            .build()

        return generateTokenFromClaims(claims, properties.jwt.expired * 60 * 1000)
    }

    fun generateRefreshToken(claims: Claims): String {
        return generateTokenFromClaims(claims, properties.jwt.expired * 60 * 1000)
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .payload

            return true
        } catch (e: MalformedJwtException) {
            throw RuntimeException("JWT token is malformed.")
        } catch (e: ExpiredJwtException) {
            throw RuntimeException("JWT token is expired.")
        } catch (e: Exception) {
            throw RuntimeException("JWT token validation failed.")
        }
    }

    fun getTokenFromHeader(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else {
            null
        }
    }

    fun getClaimsFromToken(token: String): Claims? =
        Jwts.parser()
            .verifyWith(key())
            .build()
            .parseSignedClaims(token)
            ?.payload

    private fun key() = Keys.hmacShaKeyFor(properties.jwt.secret.toByteArray())
}
