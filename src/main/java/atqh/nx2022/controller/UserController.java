package atqh.nx2022.controller;


import atqh.nx2022.pojo.User;
import atqh.nx2022.service.UserService;
import atqh.nx2022.utils.ExportWordUtils;
import com.deepoove.poi.data.PictureRenderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
//    private String origName;

    @PostMapping("/save")
    public String saveUser(User user, MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {

        if (request != null) {
//
//            System.out.println(("文件名称-->" + file.getName()));
//            System.out.println(("文件类型-->" + file.getContentType()));
//            System.out.println("文件大小-->" + file.getSize());

            //选中代码-->alt + shift + M -->命名即可`1`1`1`1`1`1`1`1`1`1`1
            fileUploadTool(user, file);
            session.setAttribute("name", user.getUsername());
            session.setAttribute("id", user.getId());
//            response.sendRedirect("/tijiao.html");


        }
        return "tijiao";
    }

//    @PostMapping("/save2")
//    public String wxSave(@RequestBody HashMap hashMap){
//
//        HashMap form = (HashMap) hashMap.get("form");
////        System.out.println(form.get("name"));
//
//        User user = new User();
//        user.setUsername(form.get("name").toString());
//        user.setEmail(form.get("email").toString());
//        user.setSex(form.get("sex").toString());
//        user.setTel(form.get("phone").toString());
//        user.setGpa(Double.parseDouble(form.get("grade").toString()));
//        user.setId(form.get("number").toString());
//        user.setMajors(form.get("class_").toString());
//        user.setDepartment(form.get("department").toString());
//        user.setAwards(form.get("rewards").toString());
//        user.setText(form.get("personalDescription").toString());
//        user.setWhy(form.get("joinReason").toString());
//        userService.saveOrUpdate(user);
//        return "保存成功";
//    }

    private void fileUploadTool(User user, MultipartFile file) {
        //将数据存储存储到数据库
        userService.saveOrUpdate(user);

        //获取项目地址
        String filename = null, path = null;
//        origName = file.getOriginalFilename();
//        path = Thread.currentThread().getContextClassLoader().getResource("").getPath()
//                + "static" + File.separator + "img" + File.separator;
        path =  "/files/img" + File.separator;
        filename = user.getId().trim().replaceAll("-", "")
                + "-" + user.getUsername() + "-" + file.getOriginalFilename();
        System.out.println("--->" + path + filename);
        File flFile = new File(path + filename);
       // 判断文件路径是否存在
        if (!flFile.getParentFile().exists()) {
            flFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(flFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //将报名信息导入word文档中
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("username", user.getUsername());
        params.put("awards", user.getAwards());
        params.put("sex", user.getSex());
        params.put("gpa", user.getGpa());
        params.put("tel", user.getTel());
        params.put("why", user.getWhy());
        params.put("text", user.getText());
        params.put("department", user.getDepartment());
        params.put("majors", user.getMajors());
        params.put("email", user.getEmail());
        params.put("Image", new PictureRenderData(100, 140, path + filename));
//        ClassPathResource oldDoc = new ClassPathResource("doc/export.docx");

        ExportWordUtils.exportWord("/files/export.docx", "/files/newDoc/", user.getUsername() + "-" + user.getId() + ".docx", params, null, null);

    }

    @RequestMapping("/down")
    public void downFile(HttpServletRequest request,
                         HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        String name = (String) session.getAttribute("name");
        String id = (String) session.getAttribute("id");
        System.out.println("!!!!!!!!!!!!!!!!!!!!开始下载");
        String fileName = name + "-"+id+".docx";
        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        String path = null;

        path = "/files/newDoc/" + name + "-" + id + ".docx";

        File file = new File(path);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置下载后的文件名以及header
            response.addHeader("Content-disposition", "attachment;fileName=" + fileName);


            // !!!!!!很重要，获取用户的流，创建输出对象
            OutputStream os = response.getOutputStream();


            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



