package decorator;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:06
 * @Description:concreteComponent
 */
public class PacketBodyCreator implements IPacketCreator{
    @Override
    public String handleContent() {
        return " content of package ";
    }
}
