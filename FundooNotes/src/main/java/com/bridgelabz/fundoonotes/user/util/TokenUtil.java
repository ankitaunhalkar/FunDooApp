package com.bridgelabz.fundoonotes.user.util;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;   
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
public class TokenUtil {
	
	static String apikey = "FundooNotes!@";
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apikey);
	    
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    //Let's set the JWT Claims
	    JwtBuilder builder = null;
		builder = Jwts.builder().setId(id)
		                            .setIssuedAt(now)
		                            .setSubject(subject)
		                            .setIssuer(issuer)
		                            .signWith(signatureAlgorithm,signingKey);
		 
   //if it has been specified, let's add the expiration
   if (ttlMillis >= 0) {
   long expMillis = nowMillis + ttlMillis;
		Date exp = new Date(expMillis);
		builder.setExpiration(exp);
   }
	    //Builds the JWT and serializes it to a compact, URL-safe string
   String tok = builder.compact();
   System.out.println(tok);
  
	    return builder.compact();
	}
		 
	//Sample method to validate and read the JWT
	public static long parseJWT(String token) {
	 
	    //This line will throw an exception if it is not a signed JWS (as expected)
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(apikey))
	       .parseClaimsJws(token).getBody();
	       
	    System.out.println(claims);
	    System.out.println(claims.getId());
	  return Long.parseLong(claims.getId());
	}
}