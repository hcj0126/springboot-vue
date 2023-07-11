package com.hcj.controller;

import com.hcj.comm.JSONController;
import com.hcj.comm.PageInfo;
import com.hcj.comm.UserCode;
import com.hcj.entity.FastUsers;
import com.hcj.handle.CacheHandle;
import com.hcj.service.IFastUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FastUsersController
 *
 * @author hcj
 * @date 2023-06-15
 */
@Controller
@RequestMapping("/manage/fastUsers")
@Slf4j
public class FastUsersController extends JSONController {

    @Autowired
    private IFastUsersService fastUsersService;

    @Autowired
    private CacheHandle cacheHandle;
    /**
     * 分页获取用户信息
     */
    @RequestMapping("/page")
    @ResponseBody
    public String findPageInfo(Long pageIndex,Long pageSize,String token,FastUsers fastUsers){
        // 先验证token
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        if(user.getType().equals(UserCode.USER_TYPE_POINT_ADMIN)){
            fastUsers.setServiceId(user.getServiceId());
        }
        log.info("分页查询用户信息的，当前页面:{}，每页条数:{}，搜索条件:{}",pageIndex,pageSize,fastUsers);
        PageInfo pageInfo = fastUsersService.findFastUsersByPage(pageIndex,pageSize,fastUsers);
        log.info("分页信息：{}",pageInfo);
        return success(pageInfo);
    }

    /**
     * 添加用户信息
     */
    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    public String add(FastUsers fastUsers){
        log.info("添加用户信息：{}",fastUsers);
        fastUsersService.add(fastUsers);
        return success("添加用户信息成功");
    }
    /**
     * 获取指定用户信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public String getFastUsersInfoById(String id){
        // 根据id调用sevice
        FastUsers fastUsers = fastUsersService.getOne(id);
        log.info("添加用户信息：{}",fastUsers);
        return success(fastUsers);
    }
    /**
     * 修改用户信息
     */
    @RequestMapping(value="upd",method = RequestMethod.POST)
    @ResponseBody
    public String updFastUsers(FastUsers fastUsers){
        log.info("修改用户信息：{}",fastUsers);
        fastUsersService.update(fastUsers);
        return success("修改用户信息成功");
    }
}
