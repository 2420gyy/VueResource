package com.hmdp.service;

import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    /**
     * @Author chenchen
     * @Description  根据id查询商户信息
     * @Date 22:56 2022/4/26
     **/
    Result queryShopById(Long id);

    /**
     * @Author chenchen
     * @Description  更新商铺信息
     * @Date 9:09 2022/4/27
     **/
    @Transactional
    Result updateShop(Shop shop);

    /**
     * @Author chenchen
     * @Description  根据id查询商户信息（基于互斥锁）
     * @Date 11:51 2022/4/27
     **/
    Result queryShopByIdWithLock(Long id);
}
