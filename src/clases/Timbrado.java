/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;

/**
 *
 * @author junju
 */
public class Timbrado {
    private Integer tipoComprobante;
    private String numeroDesde;
    private String numeroHasta;
    private String numeroTimbrado;
    private String fechaDesde;
    private String fechaHasta;

    public Timbrado() {
    }

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumeroDesde() {
        return numeroDesde;
    }

    public void setNumeroDesde(String numeroDesde) {
        this.numeroDesde = numeroDesde;
    }

    public String getNumeroHasta() {
        return numeroHasta;
    }

    public void setNumeroHasta(String numeroHasta) {
        this.numeroHasta = numeroHasta;
    }

    public String getNumeroTimbrado() {
        return numeroTimbrado;
    }

    public void setNumeroTimbrado(String numeroTimbrado) {
        this.numeroTimbrado = numeroTimbrado;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    
    
}
