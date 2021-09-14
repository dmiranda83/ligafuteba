package br.com.futeba.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import br.com.futeba.services.PasswordService;

@Service("PasswordService")
public class PasswordServiceImpl implements PasswordService {

    @Override
    public String getSecureHash(final String password) {
        return DigestUtils.sha256Hex(password);
    }
}
