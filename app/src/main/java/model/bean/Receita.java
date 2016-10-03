package model.bean;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public class Receita {

    private long id;
    private String descricao;
    private int data;
    private float valor;
    private TipoReceita tipoReceita;
    private Usuario usuario;

    public Receita(String descricao, int data, float valor, TipoReceita tipoReceita, Usuario usuario) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipoReceita = tipoReceita;
        this.usuario = usuario;
    }

    public Receita(long id, String descricao, int data, float valor, TipoReceita tipoReceita, Usuario usuario) {
        this(descricao, data, valor, tipoReceita, usuario);
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

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void validarUsuario() {
        if (getUsuario() == null)
            throw new IllegalArgumentException("Usuário da receita não pode ser nulo.");
    }

    public void validarData() {
        if (getData() == 0)
            throw new IllegalArgumentException("Data da receita não pode ser igual à zero.");
        else if (getData() < 0)
            throw new IllegalArgumentException("Data da receita não pode ser negativo.");
    }

    public void validarId() {
        if (getId() <= 0)
            throw new IllegalArgumentException("ID inválido.");

    }
}
