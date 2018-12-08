package org.vargattila;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalStrategy extends HystrixConcurrencyStrategy {

    private HystrixConcurrencyStrategy superHCS;

    public ThreadLocalStrategy(HystrixConcurrencyStrategy superHCS) {
        this.superHCS = superHCS;
    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize, HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        if (Objects.nonNull(superHCS)) {
            return superHCS.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }
        return super.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

    }

    @Override
    public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolProperties threadPoolProperties) {
        if (Objects.nonNull(superHCS)) {
            return superHCS.getThreadPool(threadPoolKey, threadPoolProperties);
        }
        return super.getThreadPool(threadPoolKey, threadPoolProperties);
    }

    @Override
    public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
        if (Objects.nonNull(superHCS)) {
            return superHCS.getBlockingQueue(maxQueueSize);
        }
        return super.getBlockingQueue(maxQueueSize);
    }

    @Override
    public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
        if (Objects.nonNull(superHCS)) {
            return superHCS.getRequestVariable(rv);
        }
        return super.getRequestVariable(rv);
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        if (Objects.nonNull(superHCS)) {
            return superHCS.wrapCallable(userContextWrapCallable(callable));
        }
        return super.wrapCallable(userContextWrapCallable(callable));
    }

    private <T> Callable<T> userContextWrapCallable(Callable<T> callable) {
        return new UserContextCallable(callable, UserContextHolder.getContext());
    }
}
