package com.ags.financemanager.controller;

import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.UsuarioServico;
import com.ags.financemanager.model.bean.Usuario;
import com.ags.financemanager.model.dao.UsuarioDAO;
import com.ags.financemanager.model.dao.UsuarioDAOImpl;

import java.util.List;


/**
 * Created by danillo on 05/10/2016.
 */

public class UsuarioControllerImpl extends BaseControllerImpl<Usuario> implements UsuarioController {

    private UsuarioDAO usuarioDAO;
    private Context contexto;
    private ExceptionHelper exceptionHelper;
    private UsuarioServico usuarioServico;

    public UsuarioControllerImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.exceptionHelper = new ExceptionHelper();
    }

    public UsuarioControllerImpl(UsuarioDAO usuarioDAO, Context ctx) {
        this.usuarioDAO = usuarioDAO;
        this.exceptionHelper = new ExceptionHelper();
        this.contexto = ctx;
        this.usuarioServico = new UsuarioServico(ctx);
    }

    public UsuarioControllerImpl(Context context) {
        this.contexto = context;
        this.usuarioDAO = new UsuarioDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
        this.usuarioServico = new UsuarioServico(context);
    }

    private void validarDAO() {
        if (usuarioDAO == null)
            throw new NullPointerException("Usuario está nulo.");
    }

    @Override
    public Usuario getUsuarioByEmail(String mail) {
        return usuarioDAO.buscarUsuarioByEmail(mail);
    }

    @Override
    public void salvar(final Usuario usuario) {
        try {

            validarDAO();
            usuarioDAO.inserirUsuario(usuario);

        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSalvarControllerException(usuario, e);
        }
    }

    @Override
    public boolean logar(String email, String senha) {
        int count = 0;
        boolean retorno = false;
        Usuario usuario = null;

        List<Usuario> usuarios = usuarioDAO.getTodos();
        count = usuarios.size();


        if (count > 0) {

            usuario = usuarioDAO.buscarUsuarioByEmail(email);

            if (usuario.getSenha().equals(senha)) {
                retorno = true;
            } else {
                retorno = false;
            }
        } else {
            UsuarioServico serv = new UsuarioServico(contexto);
            usuario = serv.login(email);

            usuarioDAO.inserirUsuario(usuario);

            if (usuario.getSenha().equals(senha)) {
                retorno = true;
            } else {
                retorno = false;
            }
        }

        return retorno;
    }

    @Override
    public void sincronizar() {

        try {
            validarDAO();
            int count = usuarioDAO.getTodos().size();

            if (count == 0) {

                List<Usuario> usuarios = usuarioServico.listarUsuarios();

                for (Usuario usuario : usuarios) {
                    salvar(usuario);
                }

            }
        } catch (Exception e) {
            if (e instanceof ControllerException)
                throw e;

            throw exceptionHelper.getNewSincronizacaoControllerException(e);
        }

    }
}
