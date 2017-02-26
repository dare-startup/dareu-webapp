package com.dareu.web.mgr.impl;

import com.dareu.web.mgr.FileManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
@Component
public class FileManagerImpl implements FileManager{

    private final String tmpFolder = System.getProperty("dareu.multipart.tmp.directory"); 
    
    @Value("${com.dareu.web.android.current.version}")
    private String apkFile; 
    
    public String saveTmpFile(InputStream file, String id) throws IOException { 
        FileOutputStream out = new FileOutputStream(new File(tmpFolder + id)); 
        byte[] bytes = new byte[1024]; 
        while (file.read(bytes) != -1 ) {
            out.write(bytes);
        }
        
        out.close();
        file.close();
        return id; 
    }

    public void deleteTmpFile(String fileName) throws FileNotFoundException{
        File file = new File(tmpFolder + fileName); 
        if(file.exists())
            file.delete(); 
        else throw new FileNotFoundException("could not find file " + tmpFolder + fileName); 
        
    }
    
    public InputStream getInputStream(String fileName)throws IOException{
        InputStream stream; 
        File file = new File(tmpFolder + fileName); 
        if(file.exists()){
            return new FileInputStream(file);
        }else throw new FileNotFoundException("Could not find file " + tmpFolder + fileName); 
    }

    public InputStream getAndroidApkFile() throws IOException {
        File file = new File(apkFile); 
        if(! file.exists())
            throw new IOException(apkFile + " does not exists"); 
        return new FileInputStream(file); 
    }
    
}
