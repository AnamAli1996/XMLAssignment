import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class myXMLParser {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {

            builder = factory.newDocumentBuilder();
            Document bookdoc = builder.parse("xmlDocument.xml");
            bookdoc.getDocumentElement().normalize();
            String rootName = bookdoc.getDocumentElement().getNodeName();
            System.out.println("root= " + rootName);
            Node root = bookdoc.getDocumentElement();
            NodeList booksLength = root.getChildNodes();
            for (int i = 0; i < booksLength.getLength(); i++) {

                Node node = booksLength.item(i);
                listNodes(booksLength.item(i));
                System.out.println("---------------------------");


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void listNodes(Node node) {
        boolean nodeText = false;
        if (node instanceof Text) {
            nodeText = true;
            String value = node.getNodeValue().trim();
            if (value.equals("")) {
                return;
            }
        }
        String nodeName = node.getNodeName();
        if(!nodeName.toString().equals("#text"))
            System.out.println("Element Name: " + nodeName.toString());
            if(!returnValue(node).equals(""))
                 System.out.println("Value: " + returnValue(node));

        System.out.println(" ");

        if (node instanceof Element && node.hasAttributes()) {
            System.out.println(" " + "Attributes are: ");
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attribute = (Attr) attrs.item(i);
                System.out.println(" " + attribute.getName() + "=" + attribute.getValue());
            }
        }

        NodeList list = node.getChildNodes();

        if (list.getLength() > 0) {
            for (int i = 0; i < list.getLength(); i++) {
                listNodes(list.item(i));
            }
        }
    }

    static String returnValue(Node node){
        if (node instanceof Text) {
            String value = node.getNodeValue().trim();
            if (!value.equals(""))
                return value;
        }
        return "";

    }

}