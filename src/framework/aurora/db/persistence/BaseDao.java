package framework.aurora.db.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import framework.aurora.db.connection.DataBaseConfiguration;
import framework.aurora.db.reflection.GetClassInformationReflection;

public class BaseDao<T> extends DataBaseConfiguration {

	private String className;

	private T t;

	private Class<?> _clazz;

	public BaseDao(Class<?> clazz) {
		super();
		this.setClassName(clazz.getSimpleName());
		newInstance(clazz);
		this._clazz = clazz;
	}

//	private void posClose(ResultSet rs) {
//		try {
//			rs.close();
//			System.out.println("connection closed!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public Boolean insertObject(T objeto) {

		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		String atributos = "";
		String valores = "";
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
					&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
					&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
				if (i < pi.getAtributos().size() - 1) {
					atributos = atributos + pi.getAtributos().elementAt(i) + ",";
					valores = valores + pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + ",";
				} else {
					atributos = atributos + pi.getAtributos().elementAt(i);
					valores = valores + pi.returnValuesMethods(pi.getAtributos().elementAt(i));
				}
			}
		}
		String sql = "insert into " + pi.getName() + "(" + atributos + ") values(" + valores + ")";

		// System.out.println(sql);
		return super.executeSQL(sql);
	}

	public Boolean insertsObjects(List<T> objetos) {
		Boolean result = false;
		for (int i = 0; i < objetos.size(); i++) {
			result = insertObject(objetos.get(i));
			if (!result) {
				return result;
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> returnObjects(T objeto) {
		String valores = "WHERE ";
		String atributos = "";
		List<T> o = new ArrayList<T>();
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i) + ",";
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
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
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
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
						objeto = ((T) pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto));
						// atributos = "";

					}
					o.add(objeto);
					objeto = null;
					objeto = ((T) pi.getClasse().getClass().newInstance());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}finally {

                try {
                    if(super.con != null) {
                        //st.close();
                        super.con.close();
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }

		} else {
			return null;
		}

		return o;
	}

	@SuppressWarnings("unchecked")
	public T returnObject(T objeto) {
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
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
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
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
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
						objeto = ((T) pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto));
						// atributos = "";

					}
					o = objeto;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

                try {
                    if(super.con != null) {
                        //st.close();
                        super.con.close();
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }

		} else {
			return null;
		}

		return (T) o;
	}

	public Boolean updateObject(T novo, T velho) {
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
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + " ";
				}
			} else {
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					if (!valores.equalsIgnoreCase("")) {
						valores = valores + " ,";
					}
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i)) + " ";
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
					atributos = atributos + pi2.getAtributos().elementAt(i) + " = "
							+ pi2.returnValuesMethods(pi.getAtributos().elementAt(i));
				}
			} else {
				if (pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)) != null
						&& !pi2.returnValuesMethods(pi2.getAtributos().elementAt(i)).equals(null)) {
					if (!atributos.equalsIgnoreCase("")) {
						atributos = atributos + " AND ";
					}
					atributos = atributos + pi2.getAtributos().elementAt(i) + " = "
							+ pi2.returnValuesMethods(pi.getAtributos().elementAt(i));
				}
			}
		}

		String sql = "UPDATE " + pi.getName() + " SET " + valores + " WHERE " + atributos;
		// System.out.println(sql);
		return executeSQL(sql);
	}

	public Boolean deleteObject(T objeto) {
		String valores = "WHERE ";
		String atributos = "";
		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);
		for (int i = 0; i < pi.getAtributos().size(); i++) {
			if (i < pi.getAtributos().size() - 1) {
				atributos = atributos + pi.getAtributos().elementAt(i);
				if (pi.returnValuesMethods(pi.getAtributos().elementAt(i)) != null
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals(null)
						&& !pi.returnValuesMethods(pi.getAtributos().elementAt(i)).equals("")) {
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
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
					valores = valores + pi.getAtributos().elementAt(i) + " = "
							+ pi.returnValuesMethods(pi.getAtributos().elementAt(i));
				}
			}
		}

		String sql = "DELETE FROM " + pi.getName();
		if (valores.length() > 6) {
			sql = "DELETE FROM " + pi.getName() + " " + valores;
		}
		// System.out.println(sql);

		return executeSQL(sql);
	}

	public Boolean sqlCommand(String sql) {
		return super.executeSQL(sql);
	}

	public List<T> sqlSearchCommand(String sql) {
		GetClassInformationReflection pi = new GetClassInformationReflection(this._clazz);
		List<T> o = new ArrayList<T>();
		o = this.resultSetToListObject(this.t, o, pi, sql);
		return o;
	}

	@SuppressWarnings("unchecked")
	private List<T> resultSetToListObject(T objeto, List<T> o, GetClassInformationReflection pi, String sql) {
		String atributos;
		ResultSet Dadosusuarios = executeSearchSQL(sql);
		if (Dadosusuarios != null) {
			try {
				while (Dadosusuarios.next()) {

					for (int i = 0; i < pi.getAtributos().size(); i++) {
						atributos = pi.getAtributos().elementAt(i);
						objeto = ((T) pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto));
					}
					o.add(objeto);
					objeto = null;
					objeto = ((T) pi.getClasse().getClass().newInstance());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} finally {

                try {
                    if(super.con != null) {
                        //st.close();
                        super.con.close();
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }

		} 
		return o;
	}

	public Boolean insertObject(String fields, String valores) {
		String sql = "";

		sql = "insert into " + getClassName() + "(" + fields + ") values(" + valores + ")";

		return sqlCommand(sql);

	}

	public List<T> returnAll() {
		return returnObjects(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> returnObjects(String fields, String where) {

		T objeto = ((T) new Object());
		String valores = "WHERE ";
		String atributos = "";
		List<T> o = new ArrayList<T>();

		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);

		if (StringUtils.isBlank(fields)) {
			fields = "*";
		}

		String sql = "SELECT " + fields + " FROM " + getClassName() + " ";

		if (StringUtils.isNotBlank(where)) {
			sql = sql + valores + where;
		}

		ResultSet Dadosusuarios = executeSearchSQL(sql);
		if (Dadosusuarios != null) {
			try {
				while (Dadosusuarios.next()) {

					for (int i = 0; i < pi.getAtributos().size(); i++) {
						atributos = pi.getAtributos().elementAt(i);
						objeto = ((T) pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto));
					}
					o.add(objeto);
					objeto = null;
					objeto = ((T) pi.getClasse().getClass().newInstance());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}finally {

                try {
                    if(super.con != null) {
                        //st.close();
                        super.con.close();
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }

		} else {
			return null;
		}

		return o;
	}

	@SuppressWarnings("unchecked")
	public T returnObject(String fields, String where) {

		T objeto = ((T) new Object());
		String valores = "WHERE ";
		String atributos = "";
		T o = ((T) new Object());

		GetClassInformationReflection pi = new GetClassInformationReflection(objeto);

		if (StringUtils.isBlank(fields)) {
			fields = "*";
		}

		String sql = "SELECT " + fields + " FROM " + getClassName() + " ";

		if (StringUtils.isNotBlank(where)) {
			sql = sql + valores + where;
		}

		ResultSet Dadosusuarios = executeSearchSQL(sql);
		if (Dadosusuarios != null) {
			try {
				while (Dadosusuarios.next()) {

					for (int i = 0; i < pi.getAtributos().size(); i++) {
						atributos = pi.getAtributos().elementAt(i);
						objeto = ((T) pi.setValuesMethods(atributos, Dadosusuarios.getObject(i + 1), objeto));

					}
					o = objeto;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

                try {
                    if(super.con != null) {
                        //st.close();
                        super.con.close();
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }

            }

		} else {
			return null;
		}

		return (T) o;

	}

	public Boolean updateObject(String setFilds, String where) {
		String sql = "UPDATE " + getClassName() + " SET " + setFilds;
		if (StringUtils.isNotBlank(where)) {
			sql = sql + " WHERE " + where;
		}
		return sqlCommand(sql);
	}

	public Boolean deleteObject(String where) {
		String sql = "DELETE FROM " + getClassName();
		if (StringUtils.isNotBlank(where)) {
			sql = sql + " WHERE " + where;
		}
		return sqlCommand(sql);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@SuppressWarnings("unchecked")
	private void newInstance(Class<?> clazz) {
		try {
			t = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
