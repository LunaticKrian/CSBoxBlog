package com.csbox.blog.service.impl;

import com.csbox.blog.entity.ArticleTag;
import com.csbox.blog.dao.ArticleTagDao;
import com.csbox.blog.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章标签服务
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagDao, ArticleTag> implements ArticleTagService {

}
