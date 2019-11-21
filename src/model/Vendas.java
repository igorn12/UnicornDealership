package model;

public class Vendas {
    private int idVendas;
    private double rendimento;

    public Vendas(int idVendas, double rendimento) {
        this.idVendas = idVendas;
        this.rendimento = rendimento;
    }

    public Vendas(double rendimento) {
        this.rendimento = rendimento;
    }

    public int getIdVendas() {
        return idVendas;
    }

    public void setIdVendas(int idVendas) {
        this.idVendas = idVendas;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
}
