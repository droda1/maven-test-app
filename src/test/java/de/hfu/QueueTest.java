package de.hfu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

    private Queue queue;

    @Before
    public void init() {
        this.queue =  new Queue(10);
    }

    @Test
    public void testEnqueue() {
        int value = 3;
        queue.enqueue(value);
        Assert.assertEquals(queue.queue[0], value);
        Assert.assertEquals(queue.tail, 0);

        int secondValue = 4;
        queue.enqueue(secondValue);
        Assert.assertEquals(queue.queue[1], secondValue);
        Assert.assertEquals(queue.tail, 1);
    }

    @Test
    public void testDequeue() {
        this.populateQueue();
        Assert.assertEquals(this.queue.tail, 9);
        Assert.assertEquals(this.queue.head, 0);

        this.queue.dequeue();
        this.queue.dequeue();
        Assert.assertEquals(this.queue.head, 2);

        this.dequeueAll();
        Assert.assertEquals(this.queue.head, 9);
    }

    @Test(expected = IllegalStateException.class, timeout = 1000)
    public void dequeueEmptyQueue() {
        this.populateQueue();
        this.dequeueAll();
        this.queue.dequeue();
    }

    @Test
    public void enqueueFullQueue() {
        this.populateQueue();
        int lastQueueEntry = queue.queue[queue.queue.length - 1];
        Assert.assertEquals(lastQueueEntry, 9);

        this.queue.enqueue(1);
        int updatedEntry = queue.queue[queue.queue.length - 1];
        Assert.assertEquals(updatedEntry, 1);
    }

    public void populateQueue() {
        for(int i = 0; i < 10; i++) {
            this.queue.enqueue(i);
        }
    }

    public void dequeueAll() {
        while(this.queue.head <= this.queue.tail) {
            this.queue.dequeue();
        }
    }
}
