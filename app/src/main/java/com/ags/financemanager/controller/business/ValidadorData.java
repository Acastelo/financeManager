package com.ags.financemanager.controller.business;

import java.util.Date;

/**
 * Created by Max on 03/10/2016.
 */
public class ValidadorData {

    public void validarDatas(Date dataInicial, Date dataFinal) {
        validarData(dataInicial);
        validarData(dataFinal);

        if (dataInicial.after(dataFinal))
            throw new IllegalArgumentException("Data final não pode ser menor que data inicial.");
    }

    public void validarData(Date date) {
        if (date == null)
            throw new IllegalArgumentException("Data não pode ser nulo.");
    }

}
