package com.csbox.blog.dao;

import com.csbox.blog.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * 留言
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Repository
public interface MessageDao extends BaseMapper<Message> {

}