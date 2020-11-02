package pers.sunny.educenter.service;

import pers.sunny.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.sunny.educenter.entity.vo.LoginVo;
import pers.sunny.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-10-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    UcenterMember getLoginInfo(String memberId);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterByDay(String day);
}
