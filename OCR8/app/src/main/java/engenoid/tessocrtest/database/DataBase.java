package engenoid.tessocrtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context)
    {
        super(context, "CADASTRO" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //amarrar o script creado
        db.execSQL(ScriptSQL.getCreateCadastro());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
