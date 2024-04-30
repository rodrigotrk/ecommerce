package br.com.i4business.store.utils;

import java.util.UUID;

public final class IdUtils {
    private IdUtils() {}

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase().replace("-", "");
    }
}
