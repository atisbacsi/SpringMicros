package org.vargattila;

import java.util.Objects;

public class UserContextHolder {

    private static ThreadLocal<UserContext> localContext = new ThreadLocal<>();

    public static UserContext getContext() {
        if (Objects.isNull(localContext.get())) {
            localContext.set(new UserContext());
        }
        return localContext.get();
    }

    public static void setContext(UserContext userContext) {
        Objects.requireNonNull(userContext);
        localContext.set(userContext);
    }
}
