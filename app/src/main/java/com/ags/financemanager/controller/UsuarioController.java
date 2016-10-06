package com.ags.financemanager.controller;

import com.ags.financemanager.model.bean.TipoReceita;
import com.ags.financemanager.model.bean.Usuario;

import java.util.List;

/**
 * Created by Max on 29/09/2016.
 */
public interface UsuarioController extends BaseController<Usuario> {
    Usuario getUsuarioByEmail(String mail);
    void salvar(Usuario usuario);
    boolean logar(String email,String senha);

}
