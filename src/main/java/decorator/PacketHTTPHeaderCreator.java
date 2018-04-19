package decorator;

import java.util.Date;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:10
 * @Description:ConcretDecorator
 */
public class PacketHTTPHeaderCreator extends PacketDecorator{
    public PacketHTTPHeaderCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handleContent() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cache-Control:no-cache\n");
        stringBuffer.append("Date:"+new Date()+"\n");
        stringBuffer.append(component.handleContent());
        return stringBuffer.toString();
    }
}
