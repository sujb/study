package decorator;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:07
 * @Description:decorator
 */
public abstract class PacketDecorator implements IPacketCreator{

    IPacketCreator component;

    public PacketDecorator(IPacketCreator component) {
        this.component = component;
    }
}
