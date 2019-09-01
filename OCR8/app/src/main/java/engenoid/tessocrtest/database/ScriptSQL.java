package engenoid.tessocrtest.database;

public class ScriptSQL {

    public static String getCreateCadastro()
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS DADOS ( ");
        sqlBuilder.append("_id                INTEGER        NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME               VARCHAR (255), ");
        sqlBuilder.append("EMAIL              VARCHAR (255) ");
        sqlBuilder.append(");" );

        return sqlBuilder.toString();
    }

}
