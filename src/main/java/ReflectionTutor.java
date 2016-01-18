
//Satisfy all TODO in the tutor.




import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTutor extends Tutor {
    final static String introspectClass = "ExampleClass1";

    @Test
    public void testReflection() {
        try {
            // TODO: load ExampleClass at runtime by name
            Class<?> clazz = Class.forName(introspectClass);
            Object obj = clazz.newInstance();

            // TODO: show all constructors (use method showConstructors())
            showConstructors(clazz);

            // TODO: list all methods, return types and arguments
            Method[] methods = clazz.getDeclaredMethods();
            for(Method m : methods) {
                System.out.println(m.getName());
                System.out.println(m.getReturnType());
                System.out.println("----------------------");
            }

            // TODO: list all fields and types of the class
            Field[] fields = clazz.getDeclaredFields();
            for(Field f : fields) {
                System.out.println(f.getName());
                System.out.println(f.getType());
                System.out.println("----------------------");
            }

            // TODO: call the printIt() method
            Method printIt = clazz.getDeclaredMethod("printIt", (Class<?>[]) null);
            printIt.invoke(obj, (Object[]) null);

            // TODO: call the printItString() method, pass a String param
            Method printItString = clazz.getDeclaredMethod("printItString", String.class);
            printItString.invoke(obj, "string");

            // TODO: call the printItInt() method, pass a int param
            Method printItInt = clazz.getDeclaredMethod("printItInt", int.class);
            printItInt.invoke(obj, 1);

            // TODO: call the setCounter() method, pass a int param
            Method setCounter = clazz.getDeclaredMethod("setCounter", int.class);
            setCounter.invoke(obj, 10);

            // TODO: call the printCounter() method
            Method printCounter = clazz.getDeclaredMethod("printCounter", (Class<?>[]) null);
            printCounter.invoke(obj, (Object[]) null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showConstructors(Class clazz) {
        // list of constructors
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constr:clazz.getConstructors()) {
            StringBuilder sb = new StringBuilder();
            for (Class param: constr.getParameterTypes()) {
                if (sb.length()>0) sb.append(", ");
                sb.append(param.getSimpleName());
            }
            sb.insert(0, "constructor: "+constr.getName()+"(");
            sb.append(")");
            log(sb.toString());
        }
        log("SuperClass: "+clazz.getSuperclass().getSimpleName());
    }

    @Test
    public void testShowConstructors() {
        showConstructors(java.lang.String.class);
    }

}


class ExampleClass1 {
    private String text;
    public String name;
    private int counter;

    public ExampleClass1() {
    }

    public ExampleClass1(String text, int counter) {
        super();
        this.text = text;
        this.counter = counter;
    }

    public void printIt(){
        System.out.println("printIt() no param");
    }

    public void printItString(String temp){
        System.out.println("printIt() with param String : " + temp);
    }

    public void printItInt(int temp){
        System.out.println("printIt() with param int : " + temp);
    }

    public void setCounter(int counter){
        this.counter = counter;
        System.out.println("setCounter() set counter to : " + counter);
    }

    public void printCounter(){
        System.out.println("printCounter() : " + this.counter);
    }

}