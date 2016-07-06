package com.company;

import com.classes.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class XmlToClass {
    public Pedido parser(Document doc) {

        NodeList request_list = doc.getElementsByTagName("pedido");
        NodeList address_list = doc.getElementsByTagName("endereco");
        NodeList client_list = doc.getElementsByTagName("cliente");
        NodeList line_item_list = doc.getElementsByTagName("itemDeLinha");
        NodeList product_list = doc.getElementsByTagName("produto");
        NodeList supplier_list = doc.getElementsByTagName("fornecedor");

        int request_id = Integer.parseInt(request_list.item(0).getAttributes().item(0).getTextContent());

        Cliente client = parseClient(client_list.item(0), parseAdress(address_list.item(0)));
        Fornecedor supplier = parseSupplier(supplier_list.item(0), parseAdress(address_list.item(1)));
        Produto produto = parseProduct(product_list.item(0), supplier);
        ItemDeLinha idl = parseLineItem(line_item_list.item(0), produto);

        return new Pedido(request_id, client, idl);
    }

    private Cliente parseClient(Node node, Endereco endereco) {
        NamedNodeMap client = node.getAttributes();

        int id = Integer.parseInt(client.item(0).getTextContent());
        String name = client.item(1).getTextContent();

        return new Cliente(id, name, endereco);
    }

    private Endereco parseAdress(Node node) {

        NamedNodeMap address = node.getAttributes();

        String city = address.item(0).getTextContent();
        String country = address.item(1).getTextContent();
        String postalCode = address.item(2).getTextContent();
        String state = address.item(3).getTextContent();
        String street = address.item(4).getTextContent();

        return new Endereco(street, city, state, postalCode, country);
    }

    private ItemDeLinha parseLineItem(Node node, Produto produto) {
        NamedNodeMap line_item = node.getAttributes();
        String qtd = line_item.item(0).getTextContent();

        return new ItemDeLinha(Integer.parseInt(qtd), produto);
    }

    private Produto parseProduct(Node node, Fornecedor supplier) {
        NamedNodeMap product_item = node.getAttributes();
        String desc = product_item.item(0).getTextContent();
        String type = product_item.item(1).getTextContent();
        String price = product_item.item(2).getTextContent();

        return new Produto(desc, Double.parseDouble(price), supplier);
    }

    private Fornecedor parseSupplier(Node node, Endereco address) {
        NamedNodeMap supplier_item = node.getAttributes();
        String id = supplier_item.item(0).getTextContent();
        return new Fornecedor(Integer.parseInt(id), address);
    }
}
