package com.ags.financemanager.model.dao;

import com.ags.financemanager.model.bean.Usuario;

/**
 * Created by Max on 04/10/2016.
 */
public interface UsuarioDAO {

    long inserirUsuario(Usuario usuario);

    Usuario buscarUsuario(long idusuario);

    boolean updateUsuario(Usuario usuario);
}
