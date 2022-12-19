package com.enatbanksc.casemanagementsystem.case_management._config.security;



import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());

    @Override
    public Map<String, Object> convert(@org.jetbrains.annotations.NotNull @NotNull Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.delegate.convert(claims);
        String username = (String) convertedClaims.get("preferred_username");
        convertedClaims.put("sub", username);
        return convertedClaims;
    }

}
