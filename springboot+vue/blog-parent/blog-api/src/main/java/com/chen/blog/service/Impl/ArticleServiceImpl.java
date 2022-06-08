package com.chen.blog.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.blog.dao.mapper.ArticleMapper;
import com.chen.blog.dao.pojo.Article;
import com.chen.blog.service.ArticleService;
import com.chen.blog.vo.ArticleVo;
import com.chen.blog.vo.Result;
import com.chen.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Result listArticle(PageParams pageParams) {
        /**
         * 分页查询数据库表
         */
        //分页规则
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //是否置顶排序
        queryWrapper.orderByDesc(Article::getWeight);
        //按时间倒序排序
        queryWrapper.orderByDesc(Article::getCreate_date);

        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        //不能直接返回，
        //不和数据库对象直接耦合
        List<ArticleVo> articleVosList = copyList(records);
        return Result.success(articleVosList);
    }

    //转义
    private List<ArticleVo> copyList(List<Article> records) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for(Article record : records){
            articleVoList.add(copy(record));
        }
        return articleVoList;
    }
    //先转义Article
    private ArticleVo copy(Article article){
        ArticleVo articleVo = new ArticleVo();
        //把相同的属性copy过来
        BeanUtils.copyProperties(article,articleVo);
        //某些字段的类型不匹配
        articleVo.setCreateDate(new DateTime(article.getCreate_date()).toString("yyyy-MM-dd HH:mm"));
        return articleVo;
    }
}
