package dao.security;

public class PasswordEncryptorImpl implements PasswordEncryptor {
    @Override
    public String encrypt(String password) {//todo
        char[] pass = password.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i] += 10;
        }
        return new String(pass);
    }

    @Override
    public String decrypt(String password) {//todo
        char[] pass = password.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i] -= 10;
        }
        return new String(pass);
    }
}
