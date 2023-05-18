package atqh.nx2022.utils;

//import com.aliyun.oss.OSS;
//import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断文件上传的类型
 */
public class FileTypeUtils {

//使用
//    String type1 = FileTypeUtils.getFileTypeByMagicNumber(file.getInputStream());
//    String type2 = FileTypeUtils.getFileTypeBySuffix(file);
//            if((type1.equals("error")||type2.equals("error"))&&!type2.toLowerCase().equals(".mp4")){
//        return "error";
//    }

    // 默认判断文件头前三个字节内容
    public static int default_check_length = 8;
    final static HashMap<String, String> fileTypeMap = new HashMap<>();
    /**
     * 允许上传的格式
     */
    private static final String[] FILE_TYPE = new String[]{
            ".bmp", ".jpg", ".jpeg", ".gif", ".png",
            ".pdf",
            ".doc", ".docx",
            ".ppt", ".pptx",
            ".mp4"
    };
    // 初始化文件头类型，不够的自行补充

    static {
        fileTypeMap.put("ffd8ffe000104a464946", ".jpg");
        fileTypeMap.put("89504e470d0a1a0a0000", ".png");
        fileTypeMap.put("47494638396126026f01", ".gif");
        fileTypeMap.put("424d228c010000000000", ".bmp");
        fileTypeMap.put("424d8240090000000000", ".bmp");
        fileTypeMap.put("424d8e1b030000000000", ".bmp");
        fileTypeMap.put("d0cf11e0", ".doc");
        fileTypeMap.put("504b0304140006000800", ".docx");
        fileTypeMap.put("255044462d312e", ".pdf");
        fileTypeMap.put("0000002066747970", ".mp4");
        fileTypeMap.put("0000001c66747970", ".mp4");
        fileTypeMap.put("41564920", ".avi");
        fileTypeMap.put("1A45DFA3A3428681", ".mkv");

        fileTypeMap.put("d0cf11e0a1b11ae1", ".ppt");
        fileTypeMap.put("504b0304", ".pptx");
    }

    /**
     * @description 通过文件头魔数获取文件类型
     */
    public static String getFileTypeByMagicNumber(InputStream inputStream) {
        byte[] bytes = new byte[default_check_length];
        try {
            // 获取文件头前三位魔数的二进制
            inputStream.read(bytes, 0, bytes.length);
            // 文件头前三位魔数二进制转为16进制
            String code = bytesToHexString(bytes);
//            System.out.println(code);
//            System.out.println(code);
            for (Map.Entry<String, String> item : fileTypeMap.entrySet()) {
                if (item.getKey().startsWith(code)||code.startsWith(item.getKey())) {
                    return item.getValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    /**
     * 通过后缀获取文件类型
     */
    public static String getFileTypeBySuffix(MultipartFile file){
        for (String type : FILE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename().toLowerCase(), type)) {
                return type;
            }
        }
        return "error";
    }

    /**
     * @description 字节数组转为16进制
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}

    /**
     * 获取地址
     * @return
     */
//    public static String getUrl(String key, OSS oss){
//        // 设置URL过期时间为1小时
//        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
//        GeneratePresignedUrlRequest generatePresignedUrlRequest ;
//        generatePresignedUrlRequest = new GeneratePresignedUrlRequest(OssUtils.BUCKET_NAME, key);
//        generatePresignedUrlRequest.setExpiration(expiration);
//        URL url = oss.generatePresignedUrl(generatePresignedUrlRequest);
//        return url.toString();
//    }
//}
/**
 *   常用文件格式
 */
/**
 private static void getAllFileType() {
 FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); // JPEG (jpg)
 FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); // PNG (png)
 FILE_TYPE_MAP.put("47494638396126026f01", "gif"); // GIF (gif)
 FILE_TYPE_MAP.put("49492a00227105008037", "tif"); // TIFF (tif)
 FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); // 16色位图(bmp)
 FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); // 24位位图(bmp)
 FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); // 256色位图(bmp)
 FILE_TYPE_MAP.put("41433130313500000000", "dwg"); // CAD (dwg)
 FILE_TYPE_MAP.put("3c21444f435459504520", "html"); // HTML (html)
 FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); // css
 FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); // js
 FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); // Rich Text Format (rtf)
 FILE_TYPE_MAP.put("38425053000100000000", "psd"); // Photoshop (psd)
 FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); // Email [Outlook Express 6] (eml)
 FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); // MS Excel 注意：word、msi 和 excel的文件头一样
 FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); // MS Access (mdb)
 FILE_TYPE_MAP.put("252150532D41646F6265", "ps");
 FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf"); // Adobe Acrobat (pdf)
 FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); // rmvb/rm相同
 FILE_TYPE_MAP.put("464c5601050000000900", "flv"); // flv与f4v相同
 FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
 FILE_TYPE_MAP.put("49443303000000002176", "mp3");
 FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //
 FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); // wmv与asf相同
 FILE_TYPE_MAP.put("4d546864000000060001", "mid"); // MIDI (mid)
 FILE_TYPE_MAP.put("504b0304140000000800", "zip");
 FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");
 FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
 FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");// 可执行文件
 FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");// jsp文件
 FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");// MF文件
 FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");// xml文件
 FILE_TYPE_MAP.put("494e5345525420494e54", "sql");// xml文件
 FILE_TYPE_MAP.put("7061636b616765207765", "java");// java文件
 FILE_TYPE_MAP.put("406563686f206f66660d", "bat");// bat文件
 FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");// gz文件
 FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");// bat文件
 FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");// bat文件
 FILE_TYPE_MAP.put("49545346030000006000", "chm");// bat文件
 FILE_TYPE_MAP.put("04000000010000001300", "mxp");// bat文件
 FILE_TYPE_MAP.put("504b0304140006000800", "docx");// docx文件
 FILE_TYPE_MAP.put("6431303a637265617465", "torrent");
 FILE_TYPE_MAP.put("cafebabe0000002e00", "class");
 FILE_TYPE_MAP.put("57415645", "wav");
 FILE_TYPE_MAP.put("41564920", "avi");
 FILE_TYPE_MAP.put("6D6F6F76", "mov"); // Quicktime (mov)
 FILE_TYPE_MAP.put("FF575043", "wpd"); // WordPerfect (wpd)
 FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); // Outlook Express (dbx)
 FILE_TYPE_MAP.put("2142444E", "pst"); // Outlook (pst)
 FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); // Quicken (qdf)
 FILE_TYPE_MAP.put("E3828596", "pwl"); // Windows Password (pwl)
 FILE_TYPE_MAP.put("2E7261FD", "ram"); // Real Audio (ram)
 FILE_TYPE_MAP.put("null", null); // null
 }
 */

