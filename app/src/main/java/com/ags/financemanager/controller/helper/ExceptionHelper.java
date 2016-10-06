package com.ags.financemanager.controller.helper;

import com.ags.financemanager.controller.exception.ControllerException;

/**
 * Created by Max on 03/10/2016.
 */
public class ExceptionHelper {

    public ControllerException getNewSalvarControllerException(Object objeto, Exception e) {
        return new ControllerException("Houve um erro ao tentar salvar as alterações de: " + objeto + ".", e);
    }

    public ControllerException getNewExcluirControllerException(Object objeto, Exception e) {
        return new ControllerException("Houve umerro ao tentar excluir: " + objeto + ".", e);
    }

    public ControllerException getNewConsultarControllerException(Exception e) {
        return new ControllerException("Houve um erro ao tentar realizar a consulta.", e);
    }

    public ControllerException getNewSincronizacaoControllerException(Exception e) {
        return new ControllerException("Houve um erro ao tentar realizar a sincronização.");
    }
}
