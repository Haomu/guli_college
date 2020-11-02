package pers.sunny.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sunny.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author sunny
 * @since 2020-10-07
 */
public interface CrmBannerService extends IService<CrmBanner> {

    Page<CrmBanner> pageBanner(long current, long limit);

    CrmBanner getBannerById(String id);

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);

    List<CrmBanner> listBanners();
}
