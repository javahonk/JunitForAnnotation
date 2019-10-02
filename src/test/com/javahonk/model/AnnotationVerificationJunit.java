package test.com.javahonk.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

public class AnnotationVerificationJunit {

    private Set<Class<? extends Object>> allClasses;

    @Before
    public void init() {
        allClasses = getClassesFromPakcage("main.com.javahonk.model");
    }

    @Test
    public void testAnnotation() throws Exception {
        Iterator it = allClasses.iterator();
        Class clazz = null;
        while (it.hasNext()) {
            clazz = (Class) it.next();
            Annotation jsonInclue = clazz.getDeclaredAnnotation(JsonInclude.class);
            Annotation jsonIgnoreProperties = clazz.getDeclaredAnnotation(JsonIgnoreProperties.class);
            Annotation jsonAutoDetectAnnotation = clazz.getAnnotation(JsonAutoDetect.class);
            Assert.assertNotNull("jsonInclueAnnotation not found: " + clazz.getName(), jsonInclue);
            Assert.assertNotNull("jsonIgnoreProperties not found: " + clazz.getName(), jsonIgnoreProperties);
            Assert.assertNotNull("jsonAutoDetectAnnotation not found: " + clazz.getName(), jsonAutoDetectAnnotation);
        }

    }

    public static Set<Class<?>> getClassesFromPakcage(String path){
        Reflections reflections = new Reflections(path, new SubTypesScanner(false));
        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
        return allClasses;
    }
}
