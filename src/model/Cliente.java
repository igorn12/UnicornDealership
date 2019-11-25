package model;

import java.util.Objects;

public class Cliente implements Comparable<Cliente>{
    private int idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private String telCliente;
    private String emailCliente;

    public Cliente(int idCliente, String nomeCliente, String cpfCliente, String telCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.telCliente = telCliente;
        this.emailCliente = emailCliente;
    }

    public Cliente(String nomeCliente, String cpfCliente, String telCliente, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.telCliente = telCliente;
        this.emailCliente = emailCliente;
    }

    public Cliente(String nomeCliente, String cpfCliente, String telCliente) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.telCliente = telCliente;
    }

    public Cliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getNomeCliente().equals(cliente.getNomeCliente()) &&
                getCpfCliente().equals(cliente.getCpfCliente());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomeCliente(), getCpfCliente());
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }

    @Override
    public int compareTo(Cliente c) {
        return this.getNomeCliente().compareTo(c.getNomeCliente());
    }
}
