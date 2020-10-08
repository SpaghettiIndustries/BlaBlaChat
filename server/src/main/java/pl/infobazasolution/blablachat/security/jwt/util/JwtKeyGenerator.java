package pl.infobazasolution.blablachat.security.jwt.util;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class JwtKeyGenerator implements KeyGenerator {
    @Override
    public Key generateKey() {
        String secret = "EcvZChPBI72EGvKeloWZ";
        Key key = new SecretKeySpec(secret.getBytes(), 0, secret.getBytes().length, "DES");
        return key;
    }
}
