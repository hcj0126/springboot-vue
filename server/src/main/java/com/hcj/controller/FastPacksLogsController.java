package com.hcj.controller;

import com.hcj.comm.JSONController;
import com.hcj.comm.PageInfo;
import com.hcj.comm.UserCode;
import com.hcj.entity.FastPacksLogs;
import com.hcj.entity.FastUsers;
import com.hcj.handle.CacheHandle;
import com.hcj.service.IFastPacksLogsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FastPacksLogsController
 *
 * @author hcj
 * @date 2023-06-16
 */
@Controller
@RequestMapping("/manage/fastPacksLogs")
@Slf4j
public class FastPacksLogsController extends JSONController {

    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private IFastPacksLogsService fastPacksLogsService;

    /**
     * 分页获取快件信息
    */
    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfo(Long pageIndex, Long pageSize, String token, FastPacksLogs fastPacksLogs){
        // 获取token
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        if(user.getType() != UserCode.USER_TYPE_ADMIN){
            // 系统管理员确定揽收网点
            fastPacksLogs.setCollectPoint(user.getServiceId());
        }
        // 调用service
        PageInfo pageInfo = fastPacksLogsService.findFastPacksLogsByPage(pageIndex, pageSize, fastPacksLogs);
        log.info("分页查询快件记录：{}",pageInfo);
        return success(pageInfo);
    }
}
