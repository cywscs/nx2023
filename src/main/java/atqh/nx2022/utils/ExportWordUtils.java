package atqh.nx2022.utils;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 导word文件模板类
 * @author azhe
 * @param ”templatePath  模板文档的位置“；
 * @param "temDir       新文件存储的位置"
 * @param “fileName    导出文件的名字”
 * @param “params      Map存放替换的匹配文本”
 *
 */
public class ExportWordUtils {
    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
//        Assert.notNull(templatePath, "模板路径不能为空");
//        Assert.notNull(temDir, "临时文件路径不能为空");
//        Assert.notNull(fileName, "导出文件名不能为空");
//        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
        if (!temDir.endsWith("/")) {
            temDir = temDir + File.separator;
        }
        File dir = new File(temDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
//            String userAgent = request.getHeader("user-agent").toLowerCase();
//            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
//                fileName = URLEncoder.encode(fileName, "UTF-8");
//            } else {
//                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
//            }
            XWPFTemplate doc = XWPFTemplate.compile(templatePath).render(params);
            String tmpPath = temDir + fileName;//新文件路径
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);
            fos.flush();
            fos.close();
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //delFileWord(temDir,fileName);//这一步看具体需求，要不要删
        }
    }
    public static void delFileWord(String filePath, String fileName){//删除文件
        File file =new File(filePath+fileName);
        File file1 =new File(filePath);
        file.delete();
        file1.delete();
    }
}
