package unit;

import com.pingpong.server.Config;
import com.pingpong.server.ProcessorContainer;
import com.pingpong.storage.Storage;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by tihon on 05.04.14.
 */
@RunWith(JUnit4.class)
public class CacheManagerTest {

    @Before
    public void before() {
        Config conf = Config.getInstance();
        conf.initServerConf();
        conf.initLoggerConf();
    }

    @Test
    public void testPingIncrementing() {
        Random random = new Random();
        StringBuilder randomUid = new StringBuilder();
        for (int i = 0; i < 10; i++)
            randomUid.append(random.nextInt());

        String uid = DigestUtils.sha1Hex(randomUid.toString());
        Storage st = ProcessorContainer.getStorateProcessor();
        assertEquals(1, st.incr(uid));
        assertEquals(2, st.incr(uid));
        assertEquals(3, st.incr(uid));
        assertEquals(4, st.incr(uid));
    }
}
