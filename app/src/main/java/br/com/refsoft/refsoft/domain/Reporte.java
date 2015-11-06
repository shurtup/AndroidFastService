package br.com.refsoft.refsoft.domain;

import java.io.Serializable;

/**
 * Created by Gabriel on 22/09/2015.
 */
public class Reporte implements Serializable {

    public long idReporte;
    public int idUsuario;
    public String tipoReporte;
    public String descricaoReporte;
    public String statusReporte;
    public String dataAbertura;
    public double latitude;
    public double longitude;
    public String endereco;
    private int banner;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(long idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getDescricaoReporte() {
        return descricaoReporte;
    }

    public void setDescricaoReporte(String descricaoReporte) {
        this.descricaoReporte = descricaoReporte;
    }

    public String getStatusReporte() {
        return statusReporte;
    }

    public void setStatusReporte(String statusReporte) {
        this.statusReporte = statusReporte;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }
}
