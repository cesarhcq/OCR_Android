package engenoid.tessocrtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import engenoid.tessocrtest.database.DataBase;
import engenoid.tessocrtest.dominio.RepositorioDados;
import engenoid.tessocrtest.dominio.entidade.Dados;

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button btnCadastrar;

    private ListView lstCadastro;
    private ArrayAdapter<Dados> adpCadastro;

    private DataBase dataBase;
    private SQLiteDatabase scriptSQL;
    private RepositorioDados repositorioDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnCadastrar = (Button) findViewById(R.id.bntCadastrar);
        lstCadastro = (ListView) findViewById(R.id.lstCadastro);

        btnCadastrar.setOnClickListener(this);
        lstCadastro.setOnItemClickListener(this);

        try {
            dataBase = new DataBase(this);
            scriptSQL = dataBase.getWritableDatabase();

            repositorioDados = new RepositorioDados(scriptSQL);

            adpCadastro = repositorioDados.buscaCadastro(this);

            lstCadastro.setAdapter(adpCadastro);

            /*AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso!");
            dlg.setNeutralButton("OK", null);
            dlg.show();*/

        }catch (SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, CadastroActivity.class);
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        adpCadastro = repositorioDados.buscaCadastro(this);

        lstCadastro.setAdapter(adpCadastro);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Dados dados = adpCadastro.getItem(position);

        Intent it = new Intent(this, CadastroActivity.class);

        it.putExtra("DADOS",dados);

        startActivityForResult(it, 0);
    }
}
