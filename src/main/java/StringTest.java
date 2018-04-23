import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @Author: sujinbo
 * @Date: 2018/4/22 00:48
 * @Description:String
 */
public class StringTest {

    /**
     * 常量池优化
     */
    @Test
    public void test() {
        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == c.intern());
    }

    /**
     * java6中substring方法有内存泄漏
     * java7中已解决
     * new String的时候，value值为截取后的char数组
     */
    @Test
    public void test1() {
        List<String> handler = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HugeStr h = new HugeStr();
//            ImprovedHugeStr h = new ImprovedHugeStr();
            handler.add(h.getSubString(1, 5));
        }
    }

    static class HugeStr {
        /**
         * 一个很长的字符串
         */
        private String str = new String(new char[10000000]);

        /**
         * java6中截取字符串，有溢出
         * java7中已解决
         *
         * @param begin
         * @param end
         * @return
         */
        public String getSubString(int begin, int end) {
            return str.substring(begin, end);
        }
    }

    static class ImprovedHugeStr {

        private String str = new String(new char[100000]);

        /**
         * 截取字符串，并重新生成
         * 新的字符串没有溢出
         *
         * @param begin
         * @param end
         * @return
         */
        public String getSubString(int begin, int end) {
            return new String(str.substring(begin, end));
        }
    }

    /**
     * String split spend -> 685
     * StringTokenizer spend -> 950
     * String indexOf spend -> 9020
     */
    @Test
    public void test2() {
        String orgStr = null;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        orgStr = sb.toString();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            orgStr.split(";");
        }
        System.out.println("String split spend -> " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(orgStr, ";");
        for (int i = 0; i < 10000; i++) {
            while (st.hasMoreTokens()) {
                st.nextToken();
            }
            st = new StringTokenizer(orgStr, ";");
        }
        System.out.println("StringTokenizer spend -> " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        String temp = orgStr;
        for (int i = 0; i < 10000; i++) {
            while (true) {
                String splitStr = null;
                int j = temp.indexOf(";");
                if (j < 0) {
                    break;
                }
                splitStr = temp.substring(0, j);
                temp = temp.substring(j + 1);
            }
            temp = orgStr;
        }
        System.out.println("String indexOf spend -> " + (System.currentTimeMillis() - begin));
    }

    /**
     * charAt spend -> 34
     * startWith spend -> 13
     */
    @Test
    public void test3() {
        String orgStr = "abcabcabcabc";
        long begin = System.currentTimeMillis();
        int len = orgStr.length();
        for (int i = 0; i < 1000000; i++) {
            if (orgStr.charAt(0) == 'a'
                    && orgStr.charAt(1) == 'b'
                    && orgStr.charAt(2) == 'c') {

            }
            if (orgStr.charAt(len - 1) == 'a'
                    && orgStr.charAt(len - 2) == 'b'
                    && orgStr.charAt(len - 3) == 'c') {

            }
        }
        System.out.println("charAt spend -> " + (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            orgStr.startsWith("abc");
            orgStr.endsWith("abc");
        }
        System.out.println("startWith spend -> " + (System.currentTimeMillis() - begin));
    }

    /**
     *  string + spend ->527
     *  string concat spend ->107
     *  stringbuffer spend ->2
     */
    @Test
    public void test4(){
        long begin = System.currentTimeMillis();
        String str = "";
        for(int i=0;i<10000;i++){
            str = str+i;
        }
        System.out.println(" string + spend ->" +(System.currentTimeMillis()-begin));
        begin = System.currentTimeMillis();
        String result = "";
        for(int i=0;i<10000;i++){
            result= result.concat(String.valueOf(i));
        }
        System.out.println(" string concat spend ->" +(System.currentTimeMillis()-begin));
        begin = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10000;i++){
            sb.append(i);
        }
        System.out.println(" stringbuffer spend ->" +(System.currentTimeMillis()-begin));
    }

    /**
     * StringBuffer线程安全
     * StringBuilder线程不安全
     * stringBuffer spend -> 86
     * stringBuilder spend -> 53
     */
    @Test
    public void test5(){
        long begin = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<500000;i++){
            stringBuffer.append(i);
        }
        System.out.println("stringBuffer spend -> " + (System.currentTimeMillis()-begin));
        begin = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<500000;i++){
            stringBuilder.append(i);
        }
        System.out.println("stringBuilder spend -> " +(System.currentTimeMillis()-begin));
    }

    /**
     * 指定初始大小
     * stringBuffer spend -> 88
     * stringBuilder spend -> 54
     */
    @Test
    public void test6(){
        long begin = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer(5888890);
        for(int i=0;i<500000;i++){
            stringBuffer.append(i);
        }
        System.out.println("stringBuffer spend -> " + (System.currentTimeMillis()-begin));
        begin = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder(5888890);
        for(int i=0;i<500000;i++){
            stringBuilder.append(i);
        }
        System.out.println("stringBuilder spend -> " +(System.currentTimeMillis()-begin));
    }

}
