package com.trainingcenter.api;
import java.util.Date;

public class Youtube {
    private long id;
    private String nomevideo;
    private String nomecanal;
    private Date dataenvio;

    public Youtube(long id, String nomevideo, String nomecanal, Date dataenvio){
        this.setId(id);
        this.setNomevideo(nomevideo);
        this.setNomecanal(nomecanal);
        this.setDataenvio(dataenvio);
    }

    public  Youtube(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomevideo() {
        return nomevideo;
    }

    public void setNomevideo(String nomevideo) {
        this.nomevideo = nomevideo;
    }

    public String getNomecanal() {
        return nomecanal;
    }

    public void setNomecanal(String nomecanal) {
        this.nomecanal = nomecanal;
    }

    public Date getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(Date dataenvio) {
        this.dataenvio = dataenvio;
    }

}
