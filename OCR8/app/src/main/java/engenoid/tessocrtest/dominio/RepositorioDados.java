package engenoid.tessocrtest.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import engenoid.tessocrtest.dominio.entidade.Dados;

public class RepositorioDados {

    private SQLiteDatabase scriptSQL;

    public RepositorioDados(SQLiteDatabase scriptSQL)
    {
        this.scriptSQL = scriptSQL;
    }

    private ContentValues preencheContentValues (Dados dados)
    {
        ContentValues values = new ContentValues();

        values.put("NOME", dados.getNome());
        values.put("EMAIL", dados.getEmail());

        return values;
    }

    //exluir dados
    public void excluir(long id)
    {
        scriptSQL.delete("DADOS", "_id = ?", new String[]{ String.valueOf( id )});
    }

    //alterar dados
    public void alterar(Dados dados)
    {
        ContentValues values = preencheContentValues(dados);
        scriptSQL.update("DADOS", values, "_id = ?", new String[]{ String.valueOf(dados.getId())});
    }

    //inserir dados
    public void inserir(Dados dados)
    {
        ContentValues values = preencheContentValues(dados);
        scriptSQL.insertOrThrow("DADOS", null, values);
    }

    /*public void testeInserirContatos()
    {

        for (int i = 0; i < 10; i++) {
            ContentValues values = new ContentValues();
            values.put("NOME", "ALAN");
            scriptSQL.insertOrThrow("DADOS", null, values);
        }

    }*/

    public ArrayAdapter<Dados> buscaCadastro(Context context)
    {
        ArrayAdapter<Dados> adpContatos = new ArrayAdapter<Dados>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = scriptSQL.query("DADOS", null, null, null, null, null, null);

        if (cursor.getCount() > 0)
        {
            cursor.moveToFirst();

            do{

                Dados dados = new Dados();

                dados.setId(cursor.getLong(cursor.getColumnIndex(Dados.ID)));
                dados.setNome(cursor.getString(cursor.getColumnIndex(Dados.NOME)));
                dados.setEmail(cursor.getString(cursor.getColumnIndex(Dados.EMAIL)));

                adpContatos.add(dados);

            }while (cursor.moveToNext());
        }

        return adpContatos;

    }

}
