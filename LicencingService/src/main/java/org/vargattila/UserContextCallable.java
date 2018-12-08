package org.vargattila;

import java.util.concurrent.Callable;

public class UserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext context;

    public UserContextCallable(Callable<V> delegate, UserContext context) {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    public V call() throws Exception {
        //Ez talán hordoz némi veszélyt, mert thread-eken át ugyanazt a UserContext-et piszkáljuk.
        UserContextHolder.setContext(context);
        try{
            return delegate.call();
        } finally {
            context = null;
        }

    }
}
