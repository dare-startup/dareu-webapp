package com.dareu.web.mgr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Alberto Rubalcaba <arubalcaba@24hourfit.com>
 */
public interface FileManager {

    /**
     * Saves a new tmp file
     *
     * @param file
     * @param id
     * @return
     * @throws IOException
     */
    public String saveTmpFile(InputStream file, String id) throws IOException;

    /**
     * delete a tmp file
     *
     * @param fileName
     * @throws java.io.FileNotFoundException
     */
    public void deleteTmpFile(String fileName) throws FileNotFoundException;

    /**
     * Get an input stream from an existing tmp file 
     * @param fileName
     * @return 
     * @throws IOException
     */
    public InputStream getInputStream(String fileName) throws IOException;
    
    /**
     * Get apk file
     * @return
     * @throws IOException 
     */
    public InputStream getAndroidApkFile()throws IOException; 
}
