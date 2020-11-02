package pers.sunny.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import pers.sunny.cmsservice.entity.CrmBanner;
import pers.sunny.cmsservice.service.CrmBannerService;
import pers.sunny.commonutils.Result;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前台前端控制器
 * </p>
 *
 * @author sunny
 * @since 2020-10-07
 */
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {

    @Resource
    private CrmBannerService crmBannerService;

    @GetMapping("/pageBanner/{current}/{limit}")
    public Result pageBanner(@PathVariable long current,@PathVariable long limit){
        Page<CrmBanner> pageBanner = crmBannerService.pageBanner(current,limit);

        return Result.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getBannerById(id);
        return Result.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("/save")
    public Result save(@RequestBody CrmBanner banner) {
        crmBannerService.saveBanner(banner);
        return Result.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("/update")
    public Result updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateBannerById(banner);
        return Result.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        crmBannerService.removeBannerById(id);
        return Result.ok();
    }

}

