package com.chen.mybatisplus.test_items.service.impl;

import com.chen.mybatisplus.test_items.entity.Items;
import com.chen.mybatisplus.test_items.mapper.ItemsMapper;
import com.chen.mybatisplus.test_items.service.IItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenChen
 * @since 2021-09-22
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

}
