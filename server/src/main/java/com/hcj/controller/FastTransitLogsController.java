package com.hcj.controller;

import com.hcj.comm.JSONController;
import com.hcj.comm.PageInfo;
import com.hcj.comm.UserCode;
import com.hcj.entity.FastTransitLogs;
import com.hcj.entity.FastUsers;
import com.hcj.handle.CacheHandle;
import com.hcj.service.IFastTransitLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FastTransitLogsController
 *
 * @author hcj
 * @date 2023-06-16
 */
@Controller
@RequestMapping("/manage/fastTransitLogs")
@Slf4j
public class FastTransitLogsController extends JSONController {

    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private IFastTransitLogsService fastTransitLogsService;

    /**
     * 分页获取运输记录
    */
    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfo(Long pageIndex, Long pageSize, String token, FastTransitLogs fastTransitLogs){
        // 获取token
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        if(user.getType()!= UserCode.USER_TYPE_ADMIN){
            // 系统管理员确定揽收网点
            fastTransitLogs.setSendPoint(user.getServiceId());
        }
        // 调用service
        PageInfo pageInfo = fastTransitLogsService.findFastTransitLogsByPage(pageIndex, pageSize, fastTransitLogs);
        log.info("分页查询运输记录：{}",pageInfo);
        return success(pageInfo);
    }
}
