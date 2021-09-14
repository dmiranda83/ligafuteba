package br.com.futeba.services;

public interface PasswordService {
    String getSecureHash(String password);
}
