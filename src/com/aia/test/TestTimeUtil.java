package com.aia.test;

import com.aia.utils.TimeUtil;
import org.junit.Test;

/**
 * Created by Jason on 2019/8/28.
 */
public class TestTimeUtil {
    @Test
    public void testGetTimeFommat(){
        String timeStr = TimeUtil.getTimeFommat();
        System.out.println(timeStr);
        assert(timeStr !=null);
    }
}
