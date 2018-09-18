package ir.pearly.zip;

/**
 * Created by Administrator on 1/24/2018.
 */

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.List;

public class AlaviZip {




    public static String Unzip(String source,String destination,String password) throws Exception  {



            ZipFile zipFile = new ZipFile(source);
            if (zipFile.isEncrypted())
                zipFile.setPassword(password);
            zipFile.extractAll(destination);
            return "0";
    }

    public static String zip(String sourcefile,String zipfilename,String password) throws Exception  {

            ZipFile zipfile = new ZipFile(zipfilename);
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            if(password.trim().length()>0) {
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                zipParameters.setPassword(password);
            }

            File tmpFile = new File(sourcefile);
            zipfile.createZipFile(tmpFile, zipParameters);

            return "0";
    }

    public static String zipf_list(List sourcefile, String zipfilename, String password) throws Exception  {

            ZipFile zipfile = new ZipFile(zipfilename);

            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(Zip4jConstants.COMP_AES_ENC);
            zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            if(password.trim().length()>0) {
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
                zipParameters.setPassword(password);
            }

            File tmpFile = new File(sourcefile.get(0).toString());
            zipfile.createZipFile(tmpFile, zipParameters);
            int i;
            if(sourcefile.size()>1){
                for(i=1;i<sourcefile.size();i++){
                    tmpFile = new File(sourcefile.get(i).toString());
                    zipfile.addFile(tmpFile, zipParameters);
                }
            }
            //zipfile.addFile(tmpFile, zipParameters);
            return "0";
    }
}
