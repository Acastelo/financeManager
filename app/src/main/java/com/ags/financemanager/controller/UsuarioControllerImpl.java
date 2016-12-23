package com.ags.financemanager.controller;
import android.content.Context;

import com.ags.financemanager.controller.exception.ControllerException;
import com.ags.financemanager.controller.helper.ExceptionHelper;
import com.ags.financemanager.controller.servicos.UsuarioServico;
import com.ags.financemanager.model.bean.Usuario;
import com.ags.financemanager.model.dao.UsuarioDAO;
import com.ags.financemanager.model.dao.UsuarioDAOImpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.ags.financemanager.model.dao.GenericDAO;


/**
 * Created by danillo on 05/10/2016.
 */

public class UsuarioControllerImpl extends BaseControllerImpl<Usuario> implements UsuarioController {

    private GenericDAO genericDAO;
    private Context contexto;
    private ExceptionHelper exceptionHelper;

    public UsuarioControllerImpl(Context context){
        this.usuarioDAO = new UsuarioDAOImpl(context);
        this.exceptionHelper = new ExceptionHelper();
    }

    public UsuarioControllerImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

    public UsuarioControllerImpl(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
        this.exceptionHelper = new ExceptionHelper();
    }

    public UsuarioControllerImpl(GenericDAO genericDAO, Context ctx) {
        this.genericDAO = genericDAO;
        this.exceptionHelper = new ExceptionHelper();
        this.contexto = ctx;

    }


    private void validarDAO() {
        if (genericDAO == null)
            throw new NullPointerException("Usuario está nulo.");
    }

    @Override
    public Usuario getUsuarioByEmail(String mail) {
        return genericDAO.buscarUsuarioByEmail(mail);
    }

    @Override
    public void salvar(Usuario usuario) {
        try {

            validarDAO();

            genericDAO.inserir(usuario);

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


        if (count > 0){

            usuario = usuarioDAO.buscarUsuarioByEmail(email);
            String senhacrip = md5(senha).toUpperCase();
            count = genericDAO.getTodos().size();


            if (usuario.getSenha().equals(senhacrip)){
                retorno = true;
            }else
            {retorno = false;}
        }else {
            UsuarioServico serv = new UsuarioServico(contexto);
            usuario = serv.login(email);

            genericDAO.inserir(usuario);

            if (usuario.getSenha().equals(senha)){
                retorno = true;
            }else
            {retorno = false;}
        }

        return retorno;
    }

    public String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
