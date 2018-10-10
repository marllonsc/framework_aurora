package framework.aurora.db.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

public class GetClassInformationReflection {

	private Object classObject;
	private String className;
	private Vector<String> attributes;
	private Vector<String> methods;
	

	public GetClassInformationReflection(Object classe) {
		super();
		this.classObject = classe;
		this.className = classe.getClass().getSimpleName();
		this.attributes = ReturnNameField(classe.getClass().getDeclaredFields());
		//this.metodos = ReturnNameMethods(classe.getClass().getDeclaredMethods());
	}

	public Object getClasse() {
		return classObject;
	}

	public void setClasse(Object classe) {
		this.classObject = classe;
	}

	public String getName() {
		return className;
	}

	public void setName(String name) {
		this.className = name;
	}

	public Vector<String> getAtributos() {
		return attributes;
	}

	public void setAtributos(Vector<String> atributos) {
		this.attributes = atributos;
	}
	
	private Vector<String> ReturnNameField(Field[] fields){
		Vector<String> campos = new Vector<>();
		for (int i = 0; i < fields.length; i++) {
			campos.add(fields[i].getName());
		}
		return campos;
	}
	
	public Vector<String> getMetodos() {
		return methods;
	}

	public void setMetodos(Vector<String> metodos) {
		this.methods = metodos;
	}
	
	public Object returnValuesMethods(String atributo){
		String tmp = atributo.substring(0,1).toUpperCase() + atributo.substring(1);
		Method m = null;
		try {
			m = this.classObject.getClass().getDeclaredMethod("get"+tmp);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return m.invoke(this.classObject);
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
				m = this.classObject.getClass().getDeclaredMethod("set"+tmp, classObject.getClass().getDeclaredField(atributo).getType());
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