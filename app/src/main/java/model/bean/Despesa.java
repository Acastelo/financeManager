package model.bean;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class Despesa {

    private long id;
    private String descricao;
    private int qtdParcelas;
    private Usuario usuario;
    private String categoriaDespesa;

    public Despesa(String descricao, int qtdParcelas, Usuario usuario, String categoriaDespesa) {
        this.descricao = descricao;
        this.qtdParcelas = qtdParcelas;
        this.usuario = usuario;
        this.categoriaDespesa = categoriaDespesa;
    }

    public Despesa(long id, String descricao, int qtdParcelas, Usuario usuario, String categoriaDespesa) {
        this(descricao, qtdParcelas, usuario, categoriaDespesa);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(String categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }
}

