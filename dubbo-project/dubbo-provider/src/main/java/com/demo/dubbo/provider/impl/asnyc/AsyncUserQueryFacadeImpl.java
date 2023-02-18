package com.demo.dubbo.provider.impl.asnyc;

import com.demo.dubbo.api.async.AsyncUserQueryFacade;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncUserQueryFacadeImpl implements AsyncUserQueryFacade {

    private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, 4,0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    @Override
    public String queryUserById(String id) {
        String resultMsg = "id = " + id + ", my name is bob";

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
}
