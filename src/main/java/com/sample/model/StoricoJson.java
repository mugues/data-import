package com.sample.model;

import org.springframework.stereotype.Component;

@Component
public class StoricoJson {
    private Long idBp;
    private String dataInserimento;
    private String messaggio;

    public Long getIdBp() {
        return idBp;
    }

    public void setIdBp(Long idBp) {
        this.idBp = idBp;
    }

    public String getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(String dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    @Override
    public String toString() {
        return "StoricoJson{" +
                "idBp='" + idBp + '\'' +
                ", dataInserimento='" + dataInserimento + '\'' +
                ", messaggio='" + messaggio + '\'' +
                '}';
    }
}
