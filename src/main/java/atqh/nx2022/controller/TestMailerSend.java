package atqh.nx2022.controller;


import atqh.nx2022.pojo.UserForEmail;
import atqh.nx2022.service.MailService;
import atqh.nx2022.service.UserForEmailService;
import atqh.nx2022.service.UserService;
import atqh.nx2022.utils.ExportWordUtils;
import atqh.nx2022.utils.RandomChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试发送邮件
 *
 * @author Xuan
 * @date 2019/8/10 15:50
 */
@RestController
@RequestMapping("/email")
public class TestMailerSend {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserForEmailService userForEmailService;

    private RandomChar randomChar;

    @Transactional
    @RequestMapping(value = "/sendtext")
    public String send() throws InterruptedException {
        List<UserForEmail> selectall = userForEmailService.selectall();
        String msg = "";
        String qq = "";
        String time = "";
        int i = 0;
        for (UserForEmail userForEmail : selectall) {
            i++;
            if (i < 35) {
                time = "周日上午(8:30-11:00)";
            } else if (i < 70) {
                time = "周日下午(14:30-17:00)";
            } else {
                time = "周六晚上(19:00-21:30)";
            }
            msg = "同学您好:<br/>我是大学生启航科技开发公司总经理赵志敏，现将周日进行的一面的相关事宜予以通知。" + "您的面试时间为" + time + "<br/>"+ "面试地点：南校实验楼(大学生创新创业孵化基地）A108" + "<br/>" +
                    "请确定好自己的学习方向，注意：目前只能选定一个方向进行学习<br/>+届时请您携带两份纸质报名表和一个精致的自己前来应聘，" +
                    "最后祝您生活愉快！<br/><hr/><h2>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp【大学生启航科技开发公司】</h2>";
            qq = userForEmail.getEmail();
            synchronized (userForEmail.getId()) {
                mailService.sendSimpleTextMailActual("【启航】周日启航一面通知", msg, new String[]{qq}, null, null, new String[]{"C:\\Users\\jz\\Desktop\\启航科技开发公司一面须知.doc"});
                System.out.println(userForEmail);
                Thread.sleep(10000);
            }
            Thread.sleep(10000);
        }
        return "邮件发送成功";
    }

    @RequestMapping("/sendfile")
    public String sendAndFile() {
        String msg = "";
        String time = "";
        int i = 0;
        if (i > 50) {
            time = "周六下午";
        } else
            time = "周六上午";
        msg = "同学您好:<br/>我是大学生启航科技开发公司总经理陈秀哲，经综合考虑，恭喜您通过本公司的第一次面试，成为储备生。<br/>" +
                "您的验证码为：" + randomChar.getlinkNo() +
                "   &nbsp请通过搜索QQ群号：923338422  或者扫描下方二维码进群，在入群验证信息部分输入正确的信息（专业班级-姓名-验证码）加入21级启航储备生QQ群。<br/>" +
                "最后祝您生活愉快！<br/><hr/>【大学生启航科技开发公司】";
        mailService.sendSimpleTextMailActual("【启航】一面面试结果通知", msg, new String[]{"473326397@qq.com"}, null, null, new String[]{"C:\\Users\\jz\\Desktop\\21级启航科技储备生群聊二维码.png"});

        return "邮件发送成功";
    }

    @Transactional
    @RequestMapping("/sendto")
    public String sendToEvery() throws InterruptedException {
        List<UserForEmail> selectall = userForEmailService.selectflag();
        String msg = "";
        String qq = "";
        for (UserForEmail userForEmail : selectall) {
            msg = userForEmail.getName() + "同学您好:<br/>我是大学生启航科技开发公司总经理陈秀哲，经综合考虑，恭喜您通过本公司的第一次面试，成为储备生。<br/>" +
                    "您的验证码为：" + randomChar.getlinkNo() +
                    "<br/> <br/>请通过搜索QQ群号：923338422  或者扫描下方二维码进群，在入群验证信息部分输入正确的信息（专业班级-姓名-验证码）加入21级启航储备生QQ群。<br/>" +
                    "最后祝您生活愉快！<br/><hr/>【大学生启航科技开发公司】";
            qq = userForEmail.getEmail();
            synchronized (userForEmail.getId()){
                mailService.sendSimpleTextMailActual("【启航】一面面试结果通知", msg, new String[]{qq}, null, null, new String []{"C:\\Users\\jz\\Desktop\\21级启航科技储备生群聊二维码.png"});
                System.out.println(userForEmail);
                System.out.println(msg);
                Thread.sleep(10000);
            }
        }

        return "邮件发送成功！！！";
    }
}
