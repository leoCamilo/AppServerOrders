
package com.classes;

public class Cliente {
    private int customerID;
    private String name;
    private Endereco endereco;

    public Cliente(int customerID, String name, Endereco endereco) {
        this.customerID = customerID;
        this.name = name;
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "customerID=" + customerID +
                ", name='" + name + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
