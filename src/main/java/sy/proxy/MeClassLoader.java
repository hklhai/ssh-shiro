package sy.proxy;

import java.io.*;

/**
 * Created by Ocean Lin on 2017/5/4.
 */
public class MeClassLoader extends ClassLoader {

    String dirString;

    public MeClassLoader(String dirString) {
        this.dirString = dirString;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(dirString + name + ".class");
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;

            try {
                while((len=fileInputStream.read(buffer))!=-1)
                {
                    outputStream.write(buffer,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.defineClass("sy.proxy."+name,outputStream.toByteArray(),0,outputStream.size());

        }

        return super.findClass(name);
    }
}
