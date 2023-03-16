package com.csbox.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csbox.blog.dto.OperationLogDTO;
import com.csbox.blog.vo.PageResult;
import com.csbox.blog.entity.OperationLog;
import com.csbox.blog.vo.ConditionVO;

/**
 * 操作日志服务
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
