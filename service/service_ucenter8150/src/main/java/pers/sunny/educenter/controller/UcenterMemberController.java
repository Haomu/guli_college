package pers.sunny.educenter.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import pers.sunny.commonutils.JwtUtils;
import pers.sunny.commonutils.Result;
import pers.sunny.commonutils.ordervo.UcenterMemberOrderVo;
import pers.sunny.educenter.entity.UcenterMember;
import pers.sunny.educenter.entity.vo.LoginVo;
import pers.sunny.educenter.entity.vo.RegisterVo;
import pers.sunny.educenter.service.UcenterMemberService;
import pers.sunny.servicebase.exception.GuliException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {
    @Resource
    private UcenterMemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        //返回token值，使用jwt生成
        String token = memberService.login(loginVo);
        return Result.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("/auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember member = memberService.getLoginInfo(memberId);
            return Result.ok().data("userInfo", member);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("/getUserInfoOrder/{id}")
    public UcenterMemberOrderVo getUserInfo(@PathVariable("id") String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrderVo memberOrder = new UcenterMemberOrderVo();
        BeanUtils.copyProperties(member,memberOrder);
        return memberOrder;
    }

    /**
     * @Author Sunny
     * @Description  查询某一天注册人数
     * @Date 2020/10/18
     * @Param [day]
     * @return pers.sunny.commonutils.Result
     **/
    @GetMapping("/countRegister/{day}")
    public Result countRegister (@PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return Result.ok().data("countRegister",count);
    }

}

