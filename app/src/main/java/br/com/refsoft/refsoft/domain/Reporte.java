package br.com.refsoft.refsoft.domain;

/**
 * Created by Gabriel on 22/09/2015.
 */
public class Reporte {

    public int idReporte;
    public int idUsuario;
    public String tipoReporte;
    public String descricaoReporte;
    public String statusReporte;
    public String dataAbertura;
    public String horaAbertura;
    public String latitude;
    public String longitude;
    public String endereco;
    private int banner;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
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

    public String getStatusReporte() {return statusReporte;}

    public void setStatusReporte(String statusReporte) {this.statusReporte = statusReporte;}

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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
