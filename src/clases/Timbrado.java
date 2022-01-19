/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.Date;
import org.threeten.bp.LocalDate;

/**
 *
 * @author junju
 */
public class Timbrado {
    /**
        @tipoComprobante
    1 = Factura; 
    2 = Nota de Débito; 
    3 = Nota de Crédito; 
    4 = Despacho; 
    5 = Auto factura; 
    6 = Boleta de Venta; 
    7 = Pasaje Aéreo; 
    8 = Factura del Exterior; 
    9 = Planilla de Sueldos; 
    10 = Comprobante de Ingresos; 
    12 = Factura de Exportación; 
    11 = Retencion Absorvida; 
    13 = Pasaje Aéreo Electrónico; 
    **/
    
    private Integer tipoComprobante;
    private Integer numeroDesde;
    private Integer numeroHasta;
    private Integer prefijoSucursal;
    private Integer prefijoPuntoExpedicion;
    private Integer numeroFacturaDesde;
    private Integer numeroFacturaHasta;
    private Integer numeroTimbrado;
    private Date fechaDesde;
    private Date fechaHasta;

    public Timbrado() {
        
    }

    public Timbrado(Integer tipoComprobante, Integer numeroDesde, Integer numeroHasta, Integer numeroTimbrado, Date fechaDesde, Date fechaHasta) {
        this.tipoComprobante = tipoComprobante;
        this.numeroDesde = numeroDesde;
        this.numeroHasta = numeroHasta;
        this.numeroTimbrado = numeroTimbrado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

   
    /*
    public void descomponerNumeros(){
        prefijoSucursal = Integer.valueOf(numeroDesde.split("-")[0]);
        prefijoPuntoExpedicion = Integer.valueOf(numeroDesde.split("-")[1]);
        numeroFacturaDesde = Integer.valueOf(numeroDesde.split("-")[2]);
        numeroFacturaHasta = Integer.valueOf(numeroHasta.split("-")[2]);
    }
*/
    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Integer getNumeroDesde() {
        return numeroDesde;
    }

    public void setNumeroDesde(Integer numeroDesde) {
        this.numeroDesde = numeroDesde;
    }

    public Integer getNumeroHasta() {
        return numeroHasta;
    }

    public void setNumeroHasta(Integer numeroHasta) {
        this.numeroHasta = numeroHasta;
    }

    public Integer getPrefijoSucursal() {
        return prefijoSucursal;
    }

    public void setPrefijoSucursal(Integer prefijoSucursal) {
        this.prefijoSucursal = prefijoSucursal;
    }

    public Integer getPrefijoPuntoExpedicion() {
        return prefijoPuntoExpedicion;
    }

    public void setPrefijoPuntoExpedicion(Integer prefijoPuntoExpedicion) {
        this.prefijoPuntoExpedicion = prefijoPuntoExpedicion;
    }

    

    public Integer getNumeroFacturaDesde() {
        return numeroFacturaDesde;
    }

    public void setNumeroFacturaDesde(Integer numeroFacturaDesde) {
        this.numeroFacturaDesde = numeroFacturaDesde;
    }

    public Integer getNumeroFacturaHasta() {
        return numeroFacturaHasta;
    }

    public void setNumeroFacturaHasta(Integer numeroFacturaHasta) {
        this.numeroFacturaHasta = numeroFacturaHasta;
    }

    public Integer getNumeroTimbrado() {
        return numeroTimbrado;
    }

    public void setNumeroTimbrado(Integer numeroTimbrado) {
        this.numeroTimbrado = numeroTimbrado;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    
    
    
}
