package pers.sunny.cmsservice.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.sunny.cmsservice.entity.CrmBanner;
import pers.sunny.cmsservice.service.CrmBannerService;
import pers.sunny.commonutils.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 后台前端控制器
 * </p>
 * @Description
 * @Author Sunny
 * @Version 1.0
 * @Date 2020-10-07-14:34
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Resource
    private CrmBannerService crmBannerService;

    @GetMapping("/banner")
    public Result listBanners(){
        List<CrmBanner> banners = crmBannerService.listBanners();
        return Result.ok().data("list",banners);
    }

}
