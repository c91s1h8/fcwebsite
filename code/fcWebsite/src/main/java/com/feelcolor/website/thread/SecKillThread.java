package com.feelcolor.website.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class SecKillThread implements Runnable {

    private String watchKey="watchKey";

    private RedisTemplate<String, Integer> redisTemplate;

    public SecKillThread(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setEnableTransactionSupport(true);
    }

    @Override
    public void run() {

        redisTemplate.watch(watchKey);
        Integer count = redisTemplate.opsForValue().get(watchKey);

        if (1 <= count && count <= 100) {

            redisTemplate.multi();   //开启事务

            redisTemplate.opsForValue().increment(watchKey, -1);     //数量减一

            List<Object> result  = redisTemplate.exec();

            if(result==null||result.size()==0){     //事务执行失败，数据已被其他线程修改
                System.out.println("线程："+Thread.currentThread().getName()+"抢购失败，事务失败。");
            }else{      //事务、数据修改成功
                System.out.println("线程："+Thread.currentThread().getName()+"抢购成功，剩余数量："+(1-(count-100)));
            }
        }else{
            System.out.println("线程："+Thread.currentThread().getName()+"抢购失败，剩余数量不足。");
        }


    }
}
