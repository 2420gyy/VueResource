package com.hmdp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.CACHE_SHOPTYPE_KEY;
import static com.hmdp.utils.RedisConstants.CACHE_SHOPTYPE_TTL;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private IShopTypeService typeService;

    /**
     * 由缓存的调用者，在更新数据库的同时更新缓存
     * @return
     */

    @Override
    public Result queryTypeList() {
        // 这里面数据的类型转换问题！！！
        String shopTypeJson = stringRedisTemplate.opsForValue().get(CACHE_SHOPTYPE_KEY);
        JSONArray objects = JSONUtil.parseArray(shopTypeJson);
        List<ShopType> shopTypes = objects.toList(ShopType.class);
        if (ObjectUtil.isNotNull(shopTypes) && shopTypes.size()!=0){
            // 命中缓存
            return Result.ok(shopTypes);
        }else {
            // 先查再放入缓存
            List<ShopType> sort = typeService.query().orderByAsc("sort").list();
            if(ObjectUtil.isNotNull(sort) && sort.size()!=0){
                stringRedisTemplate.opsForValue().set(CACHE_SHOPTYPE_KEY,JSONUtil.toJsonStr(sort));
                stringRedisTemplate.expire(CACHE_SHOPTYPE_KEY,CACHE_SHOPTYPE_TTL, TimeUnit.MINUTES);
                return Result.ok(sort);
            }else {
                return Result.fail("店铺分类不存在！");
            }
        }
    }
}
