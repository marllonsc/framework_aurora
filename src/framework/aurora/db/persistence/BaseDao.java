package framework.aurora.db.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import framework.aurora.db.connection.DataBaseConfiguration;
import framework.aurora.db.reflection.GetClassInformationReflection;

public abstract class BaseDao extends DataBaseConfiguration {
	
	private String className;
	 
	public <T> BaseDao(Class<T> objectClass) {
		super();
		this.setClassName(objectClass.getSimpleName());
	}

	public Boolean insertObject(Object objeto) {

		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		String atributos = "";
		String valores = "";
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
					&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
					&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
				if (i < pi.getAtributos().size() - 1) {
					atributos = atributos + pi.getAtributos().elementAt(i) + ",";
					valores = valores + "'" + pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "',";
				} else {
					atributos = atributos + pi.getAtributos().elementAt(i);
					valores = valores + "'" + pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			}
		}
		String sql = "insert into " + pi.getName() + "(" + atributos + ") values(" + valores + ")";

		// System.out.println(sql);
		return super.executeSql(sql);
	}

	public Boolean insertsObjects(List<Object> objetos) {
		Boolean result = false;
		for (int i = 0; i < objetos.size(); i++) {
			result = insertObject(objetos.get(i));
			if (!result) {
				return result;
			}
		}
		return result;
	}

	public List<Object> returnObjects(Object objeto) {
		String valores = "WHERE ";
		String atributos = "";
		List<Object> o = new ArrayList<Object>();
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)) != null
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals(null)
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals("")
							&& (i != pi.getAtributos().size() - 2)) {
						valores = valores + " AND ";
					}

				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				for (int j = pi.getAtributos().size() - 2; j > -1; j--) {
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(j)) != null
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(j)).equals(null)
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(j)).equals("")) {
						// valores = valores;
						j = -2;
					}
				}
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					if (!valores.equalsIgnoreCase("WHERE ")) {
						valores = valores + " AND ";
					}
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			}
		}

		String sql = "SELECT " + atributos + " FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "SELECT " + atributos + " FROM " + pi.getName() + " " + valores;
		}
		// System.out.println(sql);
		ResultSet Dadosusuarios = executeSearchSQL(sql);
		if (Dadosusuarios != null) {
			try {
				while (Dadosusuarios.next()) {

					for (int i = 0; i < pi.getAtributos().size(); i++) {
						atributos = pi.getAtributos().elementAt(i);
						objeto = pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto);
						// atributos = "";

					}
					o.add(objeto);
					objeto = null;
					objeto = pi.getClasse().getClass().newInstance();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		} else {
			return null;
		}

		
		return  o;
	}

	public Object returnObject(Object objeto) {
		String valores = "WHERE ";
		String atributos = "";
		Object o = new Object();
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)) != null
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals(null)
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals("")
							&& (i != pi.getAtributos().size() - 2)) {
						valores = valores + " AND ";
					}

				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				for (int j = pi.getAtributos().size() - 2; j > -1; j--) {
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(j)) != null
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(j)).equals(null)
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(j)).equals("")) {
						// valores = valores;
						j = -2;
					}
				}
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					if (!valores.equalsIgnoreCase("WHERE ")) {
						valores = valores + " AND ";
					}
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			}
		}

		String sql = "SELECT " + atributos + " FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "SELECT " + atributos + " FROM " + pi.getName() + " " + valores;
		}
		// System.out.println(sql);
		ResultSet Dadosusuarios = executeSearchSQL(sql);
		if (Dadosusuarios != null) {
			try {
				while (Dadosusuarios.next()) {

					for (int i = 0; i < pi.getAtributos().size(); i++) {
						atributos = pi.getAtributos().elementAt(i);
						objeto = pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto);
						// atributos = "";

					}
					o = objeto;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			return null;
		}

		return o;
	}

	public Boolean updateObject(Object novo, Object velho) {
		String valores = "";
		String atributos = "";
		GetClassInformationReflection pi = new GetClassInformationReflection(novo);
		GetClassInformationReflection pi2 = new GetClassInformationReflection(velho);

		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					if (!valores.equalsIgnoreCase("")) {
						valores = valores + " ,";
					}
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "' ";
				}
			} else {
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					if (!valores.equalsIgnoreCase("")) {
						valores = valores + " ,";
					}
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "' ";
				}
			}

		}

		for (int i = 0; i < pi2.getAtributos().size(); i++) {
			if (i < pi2.getAtributos().size() - 1) {
				if ((pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)) != null
						&& !pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)).equals(null))
						&& !pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)).equals("")) {
					if (!atributos.equalsIgnoreCase("")) {
						atributos = atributos + " AND ";
					}
					atributos = atributos + pi2.getAtributos().elementAt(i) + " ='"
							+ pi2.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			} else {
				if (pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)) != null
						&& !pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)).equals(null)) {
					if (!atributos.equalsIgnoreCase("")) {
						atributos = atributos + " AND ";
					}
					atributos = atributos + pi2.getAtributos().elementAt(i) + " ='"
							+ pi2.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			}
		}

		String sql = "UPDATE " + pi.getName() + " SET " + valores + " WHERE " + atributos;
		// System.out.println(sql);
		return executeSql(sql);
	}

	public Boolean deleteObject(Object objeto) {
		String valores = "WHERE ";
		String atributos = "";
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
					if (pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)) != null
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals(null)
							&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i + 1)).equals("")) {
						if (!valores.equalsIgnoreCase("WHERE ")) {
							valores = valores + " AND ";
						}
						valores = valores + "";
					}
				}
			} else {
				atributos = atributos + pi.getAtributos().elementAt(i);
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " ='"
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + "'";
				}
			}
		}

		String sql = "DELETE FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "DELETE FROM " + pi.getName() + " " + valores;
		}
		// System.out.println(sql);

		return executeSql(sql);
	}

	public Boolean sqlCommand(String sql) {
		return super.executeSql(sql);
	}

	public ResultSet sqlSearchCommand(String sql) {
		return super.executeSearchSQL(sql);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
