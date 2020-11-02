package pers.sunny.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cache.annotation.Cacheable;
import pers.sunny.cmsservice.entity.CrmBanner;
import pers.sunny.cmsservice.mapper.CrmBannerMapper;
import pers.sunny.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author sunny
 * @since 2020-10-07
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Resource
    private CrmBannerMapper crmBannerMapper;

    @Override
    public Page<CrmBanner> pageBanner(long current, long limit) {
        Page<CrmBanner> page = new Page<>(current,limit);
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id")
                .last(" limit 2");
        return crmBannerMapper.selectPage(page,queryWrapper);
    }

    @Override
    public CrmBanner getBannerById(String id) {
        return crmBannerMapper.selectById(id);
    }

    @Override
    public void saveBanner(CrmBanner banner) {
        crmBannerMapper.insert(banner);
    }

    @Override
    public void updateBannerById(CrmBanner banner) {
        crmBannerMapper.updateById(banner);
    }

    @Override
    public void removeBannerById(String id) {
        crmBannerMapper.deleteById(id);
    }

//    @Cacheable(value = "banner",key = "'listBanners'")
    @Override
    public List<CrmBanner> listBanners() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id")
                .last(" limit 2");
        return crmBannerMapper.selectList(queryWrapper);
    }
}
