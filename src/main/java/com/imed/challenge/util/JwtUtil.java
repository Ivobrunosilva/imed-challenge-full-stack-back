package com.imed.challenge.util;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public final class JwtUtil {

	private static final String JWT_KEY = "3njj!c%%@o--dir67-cdd3p@gkpp%rr+=8ct8hf-t47(j9dkvj-nw-y@-xs5i=^3n1crawt6v&8z$kv3p7!r&3jz7e(r1#jcxbxr!";

	public static boolean isValid(String jwt) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_KEY.getBytes())).build().parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String create(Authentication authentication) {
		String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		return Jwts.builder().setSubject(authentication.getName()).claim("authorities", authorities)
				.signWith(Keys.hmacShaKeyFor(JWT_KEY.getBytes()), SignatureAlgorithm.HS512)
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(30).toInstant())).compact();
	}

	public static Authentication getAuth(String jwt) {
		Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JWT_KEY.getBytes())).build()
				.parseClaimsJws(jwt).getBody();
		Collection<GrantedAuthority> authorities = Arrays.asList(claims.get("authorities").toString().split(","))
				.stream().filter(authority -> StringUtils.isNotBlank(authority))
				.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
		User principal = new User(claims.getSubject(), "", authorities);
		return new UsernamePasswordAuthenticationToken(principal, jwt, authorities);
	}

}
