package unit;

import com.pingpong.cache.Cache;
import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by tihon on 05.04.14.
 */
@RunWith(JUnit4.class)
public class CacheTest {

    @Before
    public void before() {
        Cache.init();
    }

    @Test
    public void testPingIncrementing() {
        Random random = new Random();
        StringBuilder randomUid = new StringBuilder();
        for(int i = 0; i < 10; i++)
            randomUid.append(random.nextInt());

        String uid = DigestUtils.sha1Hex(randomUid.toString());
        assertEquals(1, Cache.incr(uid));
        assertEquals(2, Cache.incr(uid));
        assertEquals(3, Cache.incr(uid));
        assertEquals(4, Cache.incr(uid));
    }
}
