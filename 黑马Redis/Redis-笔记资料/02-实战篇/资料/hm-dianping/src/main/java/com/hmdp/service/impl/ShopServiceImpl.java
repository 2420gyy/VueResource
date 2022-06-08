package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
    解决缓存穿透 缓存null值或者布隆过滤器
        缓存命中，返回
        命中""值，直接返回错误信息
        缓存没有去查数据库
            数据库有，存入缓存并返回
            数据库没有，存入缓存为""并设置过期时间
     */
    @Override
    public Result queryShopById(Long id) {
        // 查询缓存，没有就查数据库，然后写到缓存
        String shopJson = stringRedisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
        if(StrUtil.isNotBlank(shopJson)){
            // 缓存命中 json字符串转对象
            Shop shop = JSONUtil.toBean(shopJson,Shop.class);
            return Result.ok(shop);
        }else if(shopJson != null) {
                // 这里加一层 判断命中的是否是空值
                return Result.fail("该店铺不存在1");
            }else {
                Shop shop = baseMapper.selectById(id);
                if(ObjectUtil.isNotNull(shop)){
                    // 放到缓存，加上过期时间
                    stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id,JSONUtil.toJsonStr(shop));
                    stringRedisTemplate.expire(CACHE_SHOP_KEY + id,CACHE_SHOP_TTL, TimeUnit.MINUTES);
                    return Result.ok(shop);
                }else {
                    // 将空值写入redis
                    stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id,"");
                    stringRedisTemplate.expire(CACHE_SHOP_KEY + id,CACHE_NULL_TTL, TimeUnit.MINUTES);
                    return Result.fail("该店铺不存在2");
                }
        }
    }

    // 写的逻辑不好，然后就是用递归 P44
    @Override
    public Result queryShopByIdWithLock(Long id) {
        return null;
    }

    // 获取锁
    private boolean tryLock(String key){
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        // 防止空指针？？？
        return BooleanUtil.isTrue(aBoolean);
    }
    // 释放锁
    private void unlock(String key){
        stringRedisTemplate.delete(key);
    }




    @Override
    public Result updateShop(Shop shop) {
        if(shop.getId() == null){
            return Result.fail("店铺id不能为空");
        }
        // 修改数据库
        baseMapper.updateById(shop);
        // 删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY+shop.getId());
        return Result.ok();
    }
}
