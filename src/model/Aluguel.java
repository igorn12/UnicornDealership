package model;

import java.sql.Date;
import java.text.DateFormat;

public class Aluguel {
    private int idAluguel;
    private int idVeiculo;
    private int idCliente;
    private String locatario;
    private double valorAluguel;
    private Date dtEntrada;
    private Date dtEntrega;
    private String dataEntrada;
    private String dataDevolucao;

    public Aluguel(int idVeiculo, int idCliente, String locatario, double valorAluguel, Date dtEntrada, Date dtEntrega) {
        this.idVeiculo = idVeiculo;
        this.idCliente = idCliente;
        this.locatario = locatario;
        this.valorAluguel = valorAluguel;
        this.dtEntrada = dtEntrada;
        this.dtEntrega = dtEntrega;
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

    public double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Date getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(Date dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public Date getDtEntrega() {
        return dtEntrega;
    }

    public void setDtEntrega(Date dtEntrega) {
        this.dtEntrega = dtEntrega;
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
