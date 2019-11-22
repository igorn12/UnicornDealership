package model;

public class Vendedor implements Comparable<Vendedor>{
    private int idVendedor;
    private int totalVendas;
    private String nomeVendedor;
    private String cpfVendedor;
    private String telefone;
    private double salario;

    public Vendedor(int idVendedor, int totalVendas, String nomeVendedor, String cpfVendedor, String telefone, double salario) {
        this.idVendedor = idVendedor;
        this.totalVendas = totalVendas;
        this.nomeVendedor = nomeVendedor;
        this.cpfVendedor = cpfVendedor;
        this.telefone = telefone;
        this.salario = salario;
    }

    public Vendedor(String nomeVendedor, String cpfVendedor, String telefone, double salario) {
        this.nomeVendedor = nomeVendedor;
        this.cpfVendedor = cpfVendedor;
        this.telefone = telefone;
        this.salario = salario;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(String cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return nomeVendedor;
    }

    @Override
    public int compareTo(Vendedor vendedor) {
        return this.cpfVendedor.compareTo(vendedor.cpfVendedor);
    }
}
