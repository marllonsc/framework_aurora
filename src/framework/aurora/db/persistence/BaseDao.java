package framework.aurora.db.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import framework.aurora.db.conection.BancoDados;
import framework.aurora.db.reflection.GetClassInformationReflection;

public abstract class BaseDao extends BancoDados {

	public BaseDao(String local, String porta, String nomeBD, String usuario,
			String password) {
		super(local, porta, nomeBD, usuario, password);
	}

	public Boolean inserirObjeto(Object objeto) {

		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		String atributos = "";
		String valores = "";
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
					&& !pi.returnValuesMethods(
							pi.getAtributos().elementAt(i)).equals(null)
					&& !pi.returnValuesMethods(
							pi.getAtributos().elementAt(i)).equals("")) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				valores = valores
						+ "'"
						+ pi.returnValuesMethods(pi.getAtributos().elementAt(i))
						+ "',";
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				valores = valores
						+ "'"
						+ pi.returnValuesMethods(pi.getAtributos().elementAt(i))
						+ "'";
			}
			}
		}
		String sql = "insert into " + pi.getName() + "(" + atributos
				+ ") values(" + valores + ")";

		//System.out.println(sql);
		return super.executarSql(sql);
	}

	public Boolean inserirObjetos(Vector<Object> objetos){
		Boolean result = false;
			for (int i = 0; i < objetos.size(); i++) {
				result = inserirObjeto(objetos.elementAt(i));
				if(!result){
					return result;
				}
			}
		return result;
	} 
	
	public Vector<Object> retornaObjetos(Object objeto)    {                       
		String valores = "WHERE ";
		String atributos = "";
		Vector<Object> o = new Vector<Object>();
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i))+"'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i+1)) != null
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals(null)
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals("") && (i != pi.getAtributos().size() - 2)) {
						valores = valores +  " AND ";
					}
					
				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				for (int j = pi.getAtributos().size() -2 ; j > -1; j--) {
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(j)) != null
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(j)).equals(null)
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(j)).equals("")) {
//						valores = valores;
						j = -2;
					}
				}
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					if(!valores.equalsIgnoreCase("WHERE ")){
						valores = valores + " AND ";
					}
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
				}
			}
		}
		
		String sql = "SELECT " + atributos + " FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "SELECT " + atributos + " FROM " + pi.getName() + " "
					+ valores;
		}
		//System.out.println(sql);
		ResultSet Dadosusuarios = executarBuscaSQL(sql);
		if(Dadosusuarios != null){
		try {
			while (Dadosusuarios.next()) {

				for (int i = 0; i < pi.getAtributos().size(); i++) {
					atributos = pi.getAtributos().elementAt(i);
					objeto = pi.setValuesMethods(atributos,
							Dadosusuarios.getObject(i + 1), objeto);
					//atributos = "";
					
				}
				o.add(objeto);
				objeto = null;
				objeto = pi.getClasse().getClass().newInstance();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else{
			return null;
		}
			
		return o;
	}
	
	public Object retornaObjeto(Object objeto)    {                       
		String valores = "WHERE ";
		String atributos = "";
		Object o = new Object();
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i))+"'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i+1)) != null
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals(null)
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals("") && (i != pi.getAtributos().size() - 2)) {
						valores = valores +  " AND ";
					}
					
				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				for (int j = pi.getAtributos().size() -2 ; j > -1; j--) {
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(j)) != null
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(j)).equals(null)
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(j)).equals("")) {
//						valores = valores;
						j = -2;
					}
				}
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					if(!valores.equalsIgnoreCase("WHERE ")){
						valores = valores + " AND ";
					}
					valores = valores 
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
				}
			}
		}
		
		String sql = "SELECT " + atributos + " FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "SELECT " + atributos + " FROM " + pi.getName() + " "
					+ valores;
		}
		//System.out.println(sql);
		ResultSet Dadosusuarios = executarBuscaSQL(sql);
		if(Dadosusuarios != null){
		try {
			while (Dadosusuarios.next()) {

				for (int i = 0; i < pi.getAtributos().size(); i++) {
					atributos = pi.getAtributos().elementAt(i);
					objeto = pi.setValuesMethods(atributos,
							Dadosusuarios.getObject(i + 1), objeto);
					//atributos = "";
					
				}
				o = objeto;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		
		}
		
		}else{
			return null;
		}
			
		return o;
	}
	
	public Boolean alterarObjetos(Object novo, Object velho){
		String valores = "";
		String atributos = "";
		GetClassInformationReflection pi = new GetClassInformationReflection(novo);
		GetClassInformationReflection pi2 = new GetClassInformationReflection(velho);
		
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					if(!valores.equalsIgnoreCase("")){
						valores = valores+ " ,";
					}
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "' ";
				}
			} else {
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					if(!valores.equalsIgnoreCase("")){
						valores = valores+ " ,";
					}
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "' ";
				}
			}
			
			
		}
		
		for (int i = 0; i < pi2.getAtributos().size(); i++) {
			if (i < pi2.getAtributos().size() - 1) {
				if ((pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)) != null
						&& !pi2.returnValuesMethods(
								pi2.getAtributos().elementAt(i)).equals(null))
						&& !pi2.returnValuesMethods(
								pi2.getAtributos().elementAt(i)).equals("")) {
					if(!atributos.equalsIgnoreCase("")){
						atributos = atributos + " AND ";
					}
					atributos = atributos
							+ pi2.getAtributos().elementAt(i)
							+ " ='"
							+ pi2.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
				}
			} else {
				if (pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)) != null
						&& !pi2.returnValuesMethods(
								pi2.getAtributos().elementAt(i)).equals(null)
						) {
					if(!atributos.equalsIgnoreCase("")){
						atributos = atributos + " AND ";
					}
					atributos = atributos
							+ pi2.getAtributos().elementAt(i)
							+ " ='"
							+ pi2.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
				}
			}
		}
		
		String sql = "UPDATE " +pi.getName()+" SET "+valores+" WHERE "+atributos;
		//System.out.println(sql);
		return executarSql(sql);
	}

	public Boolean excluirObjetos(Object objeto){
		String valores = "WHERE ";
		String atributos = "";
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i+1)) != null
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals(null)
							&& !pi.returnValuesMethods(
									pi.getAtributos().elementAt(i+1)).equals("")) {
						if(!valores.equalsIgnoreCase("WHERE ")){
							valores = valores + " AND ";
						}
						valores = valores +  "";
					}
				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(
								pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores
							+ pi.getAtributos().elementAt(i)
							+ " ='"
							+ pi.returnValuesMethods(pi.getAtributos()
									.elementAt(i)) + "'";
				}
			}
		}

		String sql = "DELETE FROM "+ pi.getName();
		if (valores.length() > 6) {
			sql = "DELETE FROM " + pi.getName() + " "
					+ valores;
		}
		//System.out.println(sql);
		
		return executarSql(sql);
	}
	
	public Boolean ComandoSql(String sql) {
		return super.executarSql(sql);
	}
	
	public ResultSet ComandoBuscaSql(String sql){
		return super.executarBuscaSQL(sql);
	}

}
