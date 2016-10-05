package com.ags.financemanager.model;

/**
 * Created by Maikon Igor on 28/09/2016.
 */

public final class DBContract {

    public DBContract(){}

    public static abstract class ReceitaTable{
        public static final String TABLE_NAME= "receita";
        public static final String COL_ID = "id_receita";
        public static final String COL_DESCRICAO = "descricao";
        public static final String COL_DATA = "categoria";
        public static final String COL_VALOR = "valor";
        public static final String COL_ID_CATEGORIA_RECEITA = "id_categoria_receita";
        public static final String COL_ID_USUARIO = "id_usuario";

    }

    public static abstract class DespesaTable{
        public static final String TABLE_NAME= "despesa";
        public static final String COL_ID = "id_despesa";
        public static final String COL_DESCRICAO = "descricao";
        public static final String COL_QTD_PARCELAS = "qtd_parcelas";
        public static final String COL_ID_USUARIO = "id_usuario";
        public static final String COL_ID_CATEGORIA_DESPESA = "id_categoria_despesa";
    }

    public static abstract class UsuarioTable{
        public static final String TABLE_NAME= "usuario";
        public static final String COL_ID = "id_usuario";
        public static final String COL_NOME = "nome";
        public static final String COL_EMAIL = "email";
        public static final String COL_SENHA = "senha";
    }

    public static abstract class ItemDespesaTable{
        public static final String TABLE_NAME= "item_despesa";
        public static final String COL_ID = "id_item_despesa";
        public static final String COL_ID_DESPESA = "id_despesa";
        public static final String COL_DATA = "data";
        public static final String COL_VALOR = "valor";
    }

    public static abstract class TipoDespesaTable{
        public static final String TABLE_NAME= "tipo_despesa";
        public static final String COL_ID = "id_tipo_despesa";
        public static final String COL_DESCRICAO = "descricao";

    }

    public static abstract class TipoReceitaaTable{
        public static final String TABLE_NAME= "tipo_receita";
        public static final String COL_ID = "id_tipo_receita";
        public static final String COL_DESCRICAO = "descricao";
    }
}
