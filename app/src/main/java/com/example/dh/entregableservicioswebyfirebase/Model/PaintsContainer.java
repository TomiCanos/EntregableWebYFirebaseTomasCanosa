package com.example.dh.entregableservicioswebyfirebase.Model;

import java.util.List;

/**
 * Created by DH on 30/5/2018.
 */

public class PaintsContainer {
    private List<Paint> paints;

    public PaintsContainer() {
    }

    public void setPaints(List<Paint> paints) {
        this.paints = paints;
    }

    public List<Paint> getPaints() {
        return paints;
    }
}
