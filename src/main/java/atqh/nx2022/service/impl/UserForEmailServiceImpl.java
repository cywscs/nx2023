package atqh.nx2022.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import atqh.nx2022.pojo.UserForEmail;
import atqh.nx2022.service.UserForEmailService;
import atqh.nx2022.mapper.UserForEmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserForEmailServiceImpl extends ServiceImpl<UserForEmailMapper, UserForEmail>
        implements UserForEmailService {
    @Autowired
    private UserForEmailMapper userForEmailMapper;

    @Override
    public List<UserForEmail> selectall() {
        List<UserForEmail> userForEmails = userForEmailMapper.selectList(null);
        return userForEmails;
    }

    @Override
    public List<UserForEmail> selectflag() {
        LambdaQueryWrapper<UserForEmail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserForEmail::getFlag,1);
        List<UserForEmail> userForEmails = userForEmailMapper.selectList(queryWrapper);
        return userForEmails;
    }
}




