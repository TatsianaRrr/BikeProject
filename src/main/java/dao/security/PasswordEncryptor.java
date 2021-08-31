package dao.security;

public interface PasswordEncryptor {
    String encrypt(String password);//зашифровать
    String decrypt(String password);//расшифровать
}
