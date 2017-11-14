package BD;

import java.sql.ResultSet;

public abstract class BancoDados extends Conexao{
	
	public BancoDados(String local, String porta, String nomeBD,
			String usuario, String password) {
		super(local, porta, nomeBD, usuario, password);
	}

	protected boolean executarSql(String sql){
        return super.executarSQL(sql);
    }
    
    protected ResultSet executarBuscaSql(String sql){
        return super.executarBuscaSQL(sql);
    }
}
