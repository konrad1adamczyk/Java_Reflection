

//Create annotation @Default with the parameter which will define the default field value. Write method getFieldValue(),
//        which will get the field value or the default value, if the value is not defined (null).



/**
 * Created by KAdamczyk on 2016-01-18.
 */
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class AnnotationTutor extends Tutor {
    final static String introspectClass = "ExampleClass2";

    @Test
    public void testAnnotations()  throws ClassNotFoundException, NoSuchMethodException {
        Class<?> cls = Class.forName("ExampleClass2");
        Class noparams[] = {};
        Method method = cls.getDeclaredMethod("printIt", noparams);

        // show information about method annotated with MyAnnotation
        // print name value of the annotation
        MyAnnotation annotation =
                method.getAnnotation(MyAnnotation.class);
        boolean myAnnotation = method.isAnnotationPresent(MyAnnotation.class);
        log("my annotation name="+annotation.name());

        Field[] fields = cls.getDeclaredFields();
        for(Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if(annotations.length == 0) continue;
            Default defaultAnnotation = (Default) annotations[0];
            System.out.println(field.getName());
            System.out.println(defaultAnnotation.value());
        }
    }

    @Test
    public void testDefaultField() {
        ExampleClass2 ex = new ExampleClass2();
        String name = getFieldValue(ex,"name");
        assertEquals(name, "noname");
        ex.name = "my name";
        String name2 = getFieldValue(ex,"name");
        assertEquals(name2, "my name");
    }

    public String getFieldValue(Object obj, String fieldName) {
        Class<?> clazz = null;
        Field field = null;
        try {
            clazz = Class.forName(obj.getClass().getName());
            field = clazz.getDeclaredField(fieldName);
            String value = (String) field.get(obj);
            if(value == null) {
                Annotation[] annotations = field.getAnnotations();
                Default defaultAnnotation = (Default) annotations[0];
                value = defaultAnnotation.value();
            }
            return value;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String name() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@interface Default {
    String value();
}

class ExampleClass2 {
    @Default("my text")
    private String text;
    @Default("noname")
    public String name;
    private int counter;


    public ExampleClass2() {
    }

    public ExampleClass2(String text, int counter) {
        super();
        this.text = text;
        this.counter = counter;
    }

    @MyAnnotation(name="print me!")
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