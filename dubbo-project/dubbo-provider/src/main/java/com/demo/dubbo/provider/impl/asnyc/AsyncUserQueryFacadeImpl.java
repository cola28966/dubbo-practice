package com.demo.dubbo.provider.impl.asnyc;

import com.demo.dubbo.api.async.AsyncUserQueryFacade;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncUserQueryFacadeImpl implements AsyncUserQueryFacade {

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    @Override
    public String queryUserById(String id) {
        String resultMsg = "id = " + id + ", my name is bob";
        System.out.println(resultMsg);

        AsyncContext asyncContext = RpcContext.startAsync();
        poolExecutor.execute(() -> {
            asyncContext.signalContextSwitch();;

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            asyncContext.write(resultMsg);
        });

        return null;
    }

    @Override
    public CompletableFuture<String> queryUserByName(String name) {
        return CompletableFuture.supplyAsync(() -> {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String resultMsg = "async name = " + name + ", my name is bob";
            return resultMsg;
        });
    }
}
