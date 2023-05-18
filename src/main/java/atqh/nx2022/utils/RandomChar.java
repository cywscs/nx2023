package atqh.nx2022.utils;

import org.junit.Test;

public class RandomChar {
    public static String getlinkNo() {
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 36)];
            // 保证六位随机数之间没有重复的
            if (linkNo.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo = linkNo + c;
        }
        return linkNo;
    }

    @Test
    public void test() {
        String s = null;
        for (int i = 0; i < 20; i++) {
            s = getlinkNo();
            System.out.println(s + "\n");
        }

    }

}
