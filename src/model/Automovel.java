package model;

public class Automovel implements Comparable<Automovel>{
    private int idAutomovel;
    private int ano;
    private String tipo;
    private String modelo;
    private String placa;
    private String descricao;
    private double kmRodados;
    private double valorVenda;
    private double valorAluguel;

    public Automovel(int idAutomovel, int ano, String tipo, String modelo, String placa, String descricao, double kmRodados, double valorVenda, double valorAluguel) {
        this.idAutomovel = idAutomovel;
        this.ano = ano;
        this.tipo = tipo;
        this.modelo = modelo;
        this.placa = placa;
        this.descricao = descricao;
        this.kmRodados = kmRodados;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
    }

    public Automovel(int ano, String tipo, String modelo, String placa, String descricao, double kmRodados, double valorVenda, double valorAluguel) {
        this.ano = ano;
        this.tipo = tipo;
        this.modelo = modelo;
        this.placa = placa;
        this.descricao = descricao;
        this.kmRodados = kmRodados;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
    }

    public Automovel(int ano, String tipo, String modelo, String placa, double kmRodados, double valorVenda, double valorAluguel) {
        this.ano = ano;
        this.tipo = tipo;
        this.modelo = modelo;
        this.placa = placa;
        this.kmRodados = kmRodados;
        this.valorVenda = valorVenda;
        this.valorAluguel = valorAluguel;
    }

    public int getIdAutomovel() {
        return idAutomovel;
    }

    public void setIdAutomovel(int idAutomovel) {
        this.idAutomovel = idAutomovel;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setKmRodados(double kmRodados) {
        this.kmRodados = kmRodados;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public double getKmRodados() {
        return kmRodados;
    }

    @Override
    public String toString() {
        return modelo;
    }

    @Override
    public int compareTo(Automovel t) {
        return this.getTipo().compareTo(t.tipo);
    }
}
