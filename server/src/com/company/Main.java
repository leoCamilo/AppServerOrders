package com.company;

import com.classes.Pedido;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {
    private static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(out, "UTF-8")));
    }

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    // do something more useful in each of these handlers
                    System.out.print("ERROR");
                    exception.printStackTrace();
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.print("FATAL ERROR");
                    exception.printStackTrace();
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.out.print("WARNING");
                    exception.printStackTrace();
                }
            });

            Document doc = builder.parse("res\\Pedido.xml");
            XmlToClass xtc = new XmlToClass();
            Pedido request = xtc.parser(doc);
            printDocument(doc, System.out);
            System.out.println("\n\n --------------------------------------");
            System.out.println(request);

            MulticastSocket socket;
            DatagramPacket inPacket;
            byte[] inBuf = new byte[10000];
            try {
                //Prepare to join multicast group
                socket = new MulticastSocket(8888);
                InetAddress address = InetAddress.getByName("224.2.2.3");
                socket.joinGroup(address);
                while (true) {
                    inPacket = new DatagramPacket(inBuf, inBuf.length);
                    socket.receive(inPacket);
                    String msg = new String(inBuf, 0, inPacket.getLength());
                    System.out.print(msg);
                    InputSource is = new InputSource(new StringReader(msg));

                    doc = builder.parse(is);
                    printDocument(doc, System.out);
                    request = xtc.parser(doc);
                    System.out.println("\n\n --------------------------------------");
                    System.out.println(request);
                    // System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
            }

        } catch (Exception e) { e.printStackTrace(); }
    }
}
