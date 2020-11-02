package pers.sunny.educenter.mapper;

import pers.sunny.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author sunny
 * @since 2020-10-09
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer selectRegister(String day);
}
