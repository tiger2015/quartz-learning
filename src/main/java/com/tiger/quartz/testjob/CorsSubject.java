package com.tiger.quartz.testjob;

import com.tiger.quartz.QuartzTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class CorsSubject {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsSubject.class);
    private int id;
    private LinkedBlockingDeque<Long> queue;

    public CorsSubject(int id) {
        this.id = id;
        this.queue = new LinkedBlockingDeque<>();
    }

    public void noity(long id) {
        try {
            queue.put(id);
            QuartzTest.addHandoutMessageJob(this, id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncBaseline() {
        Long id = queue.poll();
        if (id == null) {
            return;
        }
        synchronized (this) {
            try {
                LOGGER.info("cors-" + id + " synchronize baseline");
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {

            }
            for(;;)
            {

            }
        }
    }

    public void handoutMessage() {
        synchronized (this) {

            try {
                LOGGER.info("cors-" + id + " hand message");
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public int getId() {
        return id;
    }
}
