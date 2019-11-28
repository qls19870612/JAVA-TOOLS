package sample.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

    /**
     * LOCAL_PATH 文件存储的位置
     * fileUrl 待下载文件地址
     * type 文件类型  jpg,png,mp3...
     * @return
     */
    public static Boolean FileDown(String LOCAL_PATH, String subFolder, String fileUrl) {

        InputStream in = null;
        OutputStream out = null;
        HttpURLConnection conn = null;
        String fileName = getFileName(fileUrl);
        Boolean isLoaded = false;
        try {
            //初始化连接
            URL url = new URL(fileUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //获取文件名
            //            String disposition=conn.getHeaderField("Content-Disposition");
            //            if(disposition!=null&&!"".equals(disposition)){
            //                //从头中获取文件名
            //                fileName=disposition.split(";")[1].split("=")[1].replaceAll("\"","");
            //            }else{
            //                //从地址中获取文件名
            //                fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1);
            //            }
            //
            //            if(fileName!=null&&!"".equals(fileName)){
            //                //文件名解码
            //                fileName=URLDecoder.decode(fileName, "utf-8")+".jpg";
            //            }else{
            //                //如果无法获取文件名，则随机生成一个
            //                fileName="file_"+(int)(Math.random()*10)+type;
            //            }

            System.out.println("conn.getResponseCode():" + conn.getResponseCode());
            //读取数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                byte[] buffer = new byte[2048];
                in = conn.getInputStream();
                File file = new File(LOCAL_PATH + "/" + subFolder, fileName);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                out = new FileOutputStream(file);
                int count = 0;
                int finished = 0;
                int size = conn.getContentLength();
                while ((count = in.read(buffer)) != -1) {
                    if (count != 0) {
                        out.write(buffer, 0, count);
                        finished += count;
                        // System.out.printf("---->%1$.2f%%\n",(double)finished/size*100);
                    } else {
                        break;
                    }
                }
                isLoaded = true;
                System.out.println("downloaded :" + file.getPath());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {

                    out.close();
                    in.close();
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return isLoaded;
    }

    private static String getFileName(String fileUrl) {
        if (fileUrl != null) {
            String[] fileNames = fileUrl.split("\\?");
            fileUrl = fileNames[0];
            fileNames = fileUrl.split("\\/");
            return fileNames[fileNames.length - 1];
        }
        return null;
    }


}