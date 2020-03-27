package com.cake.modules.cms.service.impl;

import com.cake.modules.cms.entity.Article;
import com.cake.modules.cms.mapper.ArticleMapper;
import com.cake.modules.cms.service.IArticleService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: Article
 * @Author: eamiear
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
