package pl.infobazasolution.blablachat.security.jwt.util;

import java.security.Key;

public interface KeyGenerator {
    Key generateKey();
}
