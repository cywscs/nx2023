package atqh.nx2022.service;

import atqh.nx2022.pojo.UserForEmail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface UserForEmailService extends IService<UserForEmail> {

    List<UserForEmail> selectall();

    List<UserForEmail> selectflag();
}
