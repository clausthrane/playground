package cth.alg.sort;


import com.google.common.collect.Lists;
import org.apache.commons.lang.RandomStringUtils;

import java.util.List;

public class SortTestUtils {

    public static List<String> randomList(int size) {
        //int i = RandomUtils.nextInt(size);
        List<String> list = Lists.newArrayList();
        for (int j = 0; j < size; j++) {
            list.add(RandomStringUtils.randomNumeric(1));
        }
        return list;
    }
}
