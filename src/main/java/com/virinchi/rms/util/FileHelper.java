/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virinchi.rms.util;

/**
 *
 * @author Risab
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Risab
 */
public class FileHelper {
    public static String readToEnd(String fileName) throws IOException{
        StringBuilder content=new StringBuilder();
        String line="";
        try (BufferedReader reader =new BufferedReader(new FileReader(fileName))){
            while((line=reader.readLine())!=null){
                content.append(line).append("\r\n");
            }
            
        }
        return content.toString();
    }
    
    public static void write(String fileName, String content)throws IOException{
        FileWriter writer=new FileWriter(fileName);
                writer.write(content);
                writer.close();
    }
    
}
