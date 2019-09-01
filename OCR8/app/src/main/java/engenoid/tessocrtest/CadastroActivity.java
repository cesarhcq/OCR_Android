package engenoid.tessocrtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import engenoid.tessocrtest.database.DataBase;
import engenoid.tessocrtest.dominio.RepositorioDados;
import engenoid.tessocrtest.dominio.entidade.Dados;;

public class CadastroActivity extends Activity{

    private Button btnSalvar;
    private Button btnExcluir;
    private Button btnExecultar;
    private EditText edtNome;
    private EditText edtEmail;

    private DataBase dataBase;
    private SQLiteDatabase scriptSQL;
    private RepositorioDados repositorioDados;
    private Dados dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuario);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExecultar = (Button) findViewById(R.id.btnExecultar);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("DADOS")))
        {
            dados = (Dados)bundle.getSerializable("DADOS");
            preencheDados();
        }
        else
            dados = new Dados();

        try {
            dataBase = new DataBase(this);
            scriptSQL = dataBase.getWritableDatabase();

            repositorioDados = new RepositorioDados(scriptSQL);

            /*
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso!");
            dlg.setNeutralButton("OK", null);
            dlg.show();*/

        }catch (SQLException ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar DADOS: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
                Intent itmain = new Intent(CadastroActivity.this, MainActivity.class);
                startActivityForResult(itmain, 2);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excluir();
                Intent itmain = new Intent(CadastroActivity.this, MainActivity.class);
                startActivityForResult(itmain, 2);
            }
        });

        btnExecultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foto = new Intent(CadastroActivity.this, FotoFrameWork.class);
                startActivityForResult(foto, 3);
            }
        });
    }


    private void preencheDados()
    {
        edtNome.setText(dados.getNome());
        edtEmail.setText(dados.getEmail());

    }

    private void excluir()
    {
        try
        {
            repositorioDados.excluir(dados.getId());

        }catch (Exception ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao excluir dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    private void salvar()
    {
        try {

            dados.setNome(edtNome.getText().toString());
            dados.setEmail(edtEmail.getText().toString());

            if(dados.getId() == 0)
            {
                repositorioDados.inserir(dados);
            }
            else {

                repositorioDados.alterar(dados);
            }
        }catch (Exception ex)
        {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
