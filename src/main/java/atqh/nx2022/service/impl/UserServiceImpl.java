package atqh.nx2022.service.impl;


import atqh.nx2022.mapper.UserMapper;
import atqh.nx2022.pojo.User;
import atqh.nx2022.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService  {

}




