package model;

public class Aluguel {
    private int idAluguel;
    private int idVeiculo;
    private int idCliente;
    private String dataEntrada;
    private String dataDevolucao;
    private String locatario;


    public Aluguel(int idVeiculo, int idCliente, String dataEntrada, String dataDevolucao, String locatario) {
        this.idVeiculo = idVeiculo;
        this.idCliente = idCliente;
        this.dataEntrada = dataEntrada;
        this.dataDevolucao = dataDevolucao;
        this.locatario = locatario;
    }

    public Aluguel(int idAluguel, String dataEntrada, String dataDevolucao, String locatario) {
        this.idAluguel = idAluguel;
        this.dataEntrada = dataEntrada;
        this.dataDevolucao = dataDevolucao;
        this.locatario = locatario;
    }

    public Aluguel(String dataEntrada, String dataDevolucao, String locatario) {
        this.dataEntrada = dataEntrada;
        this.dataDevolucao = dataDevolucao;
        this.locatario = locatario;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getLocatario() {
        return locatario;
    }

    public void setLocatario(String locatario) {
        this.locatario = locatario;
    }

    @Override
    public String toString() {
        return "Alugado entre{ " + dataEntrada+" até " +dataDevolucao+", por: "+locatario+" }";
    }
}
