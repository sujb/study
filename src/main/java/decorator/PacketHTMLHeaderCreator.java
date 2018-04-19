package decorator;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:08
 * @Description:ConcretDecorator
 */
public class PacketHTMLHeaderCreator extends PacketDecorator{

    public PacketHTMLHeaderCreator(IPacketCreator component) {
        super(component);
    }

    @Override
    public String handleContent() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html>");
        stringBuffer.append("<body>");
        stringBuffer.append(component.handleContent());
        stringBuffer.append("</body>");
        stringBuffer.append("</html>");
        return stringBuffer.toString();
    }
}
