package model.bean;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class Receita {

    private long id;
    private String descricao;
    private int data;
    private float valor;
    private String categoriaReceita;
    private Usuario usuario;

    public Receita(String descricao, int data, float valor, String categoriaReceita, Usuario usuario) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.categoriaReceita = categoriaReceita;
        this.usuario = usuario;
    }

    public Receita(long id, String descricao, int data, float valor, String categoriaReceita, Usuario usuario) {
        this(descricao, data, valor, categoriaReceita, usuario);
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoriaReceita() {
        return categoriaReceita;
    }

    public void setCategoriaReceita(String categoriaReceita) {
        this.categoriaReceita = categoriaReceita;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
