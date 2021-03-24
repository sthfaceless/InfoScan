package ru.sthfaceless.qscan.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author danil
 * @date 21.04.19
 */
@Service
public class TokenService {

    private final SecureRandom secureRandom;

    private final Base64.Encoder encoder;

    private final byte[] buffer;

    public TokenService(@Value("${security.token.length}") Integer tokenSize) {
        this.secureRandom =  new SecureRandom();
        this.encoder = Base64.getEncoder();
        this.buffer = new byte[tokenSize/2];
    }

    public String nextToken(){

        secureRandom.nextBytes(this.buffer);

        return encoder.encodeToString(this.buffer);
    }
}
