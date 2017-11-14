package CasseInformation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class PegarInformaion {

	private Object classe;
	private String name;
	private Vector<String> atributos;
	private Vector<String> metodos;
	

	public PegarInformaion(Object classe) {
		super();
		this.classe = classe;
		this.name = classe.getClass().getSimpleName();
		this.atributos = ReturnNameField(classe.getClass().getDeclaredFields());
		//this.metodos = ReturnNameMethods(classe.getClass().getDeclaredMethods());
	}

	public Object getClasse() {
		return classe;
	}

	public void setClasse(Object classe) {
		this.classe = classe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<String> getAtributos() {
		return atributos;
	}

	public void setAtributos(Vector<String> atributos) {
		this.atributos = atributos;
	}
	
	private Vector<String> ReturnNameField(Field[] fields){
		Vector<String> campos = new Vector<>();
		for (int i = 0; i < fields.length; i++) {
			campos.add(fields[i].getName());
		}
		return campos;
	}
	
	public Vector<String> getMetodos() {
		return metodos;
	}

	public void setMetodos(Vector<String> metodos) {
		this.metodos = metodos;
	}
	
	public Object returnValuesMethods(String atributo){
		String tmp = atributo.substring(0,1).toUpperCase() + atributo.substring(1);
		Method m = null;
		try {
			m = this.classe.getClass().getDeclaredMethod("get"+tmp);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return m.invoke(this.classe);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object setValuesMethods(String atributo, Object value,  Object o){
		String tmp = atributo.substring(0,1).toUpperCase() + atributo.substring(1);
		//String tmp2 = atributo.substring(0,1) + atributo.substring(1);
	    Method m = null;
		try {
			try {
				m = this.classe.getClass().getDeclaredMethod("set"+tmp, classe.getClass().getDeclaredField(atributo).getType());
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			m.invoke(o, value);
			//classe = null;
			//classe = o;
			//o = o.getClass().newInstance();
			return o;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (InstantiationException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return null;
	}
}
