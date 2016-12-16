package com.ags.financemanager.model.dao;

import com.ags.financemanager.model.bean.Usuario;

import java.util.List;

/**
 * Created by Max on 04/10/2016.
 */
public interface GenericDAO<T> {

    long inserir(Usuario usuario);

    Usuario buscarUsuario(long idusuario);

    Usuario buscarUsuarioByEmail(String email);

    boolean updateUsuario(Usuario usuario);

    boolean excluirUsuario(Usuario usuario);

    List<Usuario> getTodos();
}
