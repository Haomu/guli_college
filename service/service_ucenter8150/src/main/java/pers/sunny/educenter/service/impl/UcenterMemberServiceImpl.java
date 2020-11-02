package pers.sunny.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import pers.sunny.commonutils.JwtUtils;
import pers.sunny.commonutils.MD5;
import pers.sunny.educenter.entity.UcenterMember;
import pers.sunny.educenter.entity.vo.LoginVo;
import pers.sunny.educenter.entity.vo.RegisterVo;
import pers.sunny.educenter.mapper.UcenterMemberMapper;
import pers.sunny.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sunny.servicebase.exception.GuliException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-10-09
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private UcenterMemberMapper ucenterMemberMapper;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuliException(20001,"参数异常");
        }
        //获取会员
        UcenterMember member = ucenterMemberMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (member == null){
            throw new GuliException(20001,"不是会员，请注册");
        }

        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())){
            throw new GuliException(20001,"密码错误");
        }

        //校验是否被禁用
        if (member.getIsDisabled()){
            throw new GuliException(20001,"该账户已被禁用，请联系管理员");
        }

        //使用jwt生成字符串
        return JwtUtils.getJwtToken(member.getId(),member.getNickname());

    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new GuliException(20001,"error");
        }
        //校验验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(mobileCode)) {
            throw new GuliException(20001,"验证码错误");
        }

        //查询是否存在相同的手机号
        Integer count = ucenterMemberMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count > 0){
            throw new GuliException(20001,"该手机号已被注册");
        }

        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        ucenterMemberMapper.insert(member);
    }

    @Override
    public UcenterMember getLoginInfo(String memberId) {
        return ucenterMemberMapper.selectById(memberId);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",openid);
        return ucenterMemberMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return ucenterMemberMapper.selectRegister(day);
    }
}
