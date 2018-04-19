package decorator;

import org.junit.Test;

import java.io.*;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:13
 * @Description:装饰模式，有效分离性能组件和功能组件，提升模块的可维护性和复用性
 */
public class DecoratorTest {

    @Test
    public void test1() {
        IPacketCreator pc = new PacketHTTPHeaderCreator(new PacketHTMLHeaderCreator(new PacketBodyCreator()));
        System.out.println(pc.handleContent());
    }

    @Test
    public void test2() throws IOException {
        //使用性能组件spend:28
//        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("a.txt")));

        //不使用性能组件spend:408
        DataOutputStream dout = new DataOutputStream(new FileOutputStream("b.txt"));
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            dout.write(i);
        }
        System.out.println("spend:" + (System.currentTimeMillis() - begin));

    }

}
