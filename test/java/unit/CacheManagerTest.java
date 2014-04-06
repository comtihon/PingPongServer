package unit;

import com.pingpong.manager.CacheManager;
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
public class CacheManagerTest {

    @Before
    public void before() {
        CacheManager.init();
    }

    @Test
    public void testPingIncrementing() {
        Random random = new Random();
        StringBuilder randomUid = new StringBuilder();
        for(int i = 0; i < 10; i++)
            randomUid.append(random.nextInt());

        String uid = DigestUtils.sha1Hex(randomUid.toString());
        assertEquals(1, CacheManager.incr(uid));
        assertEquals(2, CacheManager.incr(uid));
        assertEquals(3, CacheManager.incr(uid));
        assertEquals(4, CacheManager.incr(uid));
    }
}
