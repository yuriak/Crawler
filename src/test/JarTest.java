package test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class JarTest {
	public static void loadJarAndRun() throws Throwable{
//		String fileName="lib/HelloWorld.jar";
//		String mainClassName = null;  
//        JarFile jarFile;  
//        jarFile = new JarFile(fileName);  
//        Manifest manifest = jarFile.getManifest();  
//        if (manifest != null) {  
//            mainClassName = manifest.getMainAttributes().getValue("Main-Class");  
//        }
//        jarFile.close();
        MyClassLoader classLoader=new MyClassLoader(new URL[]{});
        classLoader.addJar(new File("lib/HelloWorld.jar").toURI().toURL());
        Class<?> clazz=classLoader.loadClass("hello.Crawl");
        Object object=clazz.newInstance();
        Method method=clazz.getMethod("start");
        method.invoke(object);
        classLoader.close();
	}
	
	public static void main(String[] args) throws Throwable {
		JarTest.loadJarAndRun();
	}
	
	static class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public void addJar(URL url) {
            this.addURL(url);
        }

    }
}
