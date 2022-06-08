package com.chen.blog.controller;

import com.chen.blog.service.ArticleService;
import com.chen.blog.vo.Result;
import com.chen.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//都是json格式交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     *
     *首页文章列表
     */
    @PostMapping("")
    public Result listArticle(@RequestBody PageParams pageParams){


        return articleService.listArticle(pageParams);
    }
}
