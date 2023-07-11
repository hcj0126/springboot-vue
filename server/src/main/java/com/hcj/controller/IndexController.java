package com.hcj.controller;

import com.hcj.comm.JSONController;
import com.hcj.entity.FastUsers;
import com.hcj.handle.CacheHandle;
import com.hcj.service.IFastUsersService;
import com.hcj.service.ISysStatucInfo;
import com.hcj.util.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * IndexController
 *
 * @author hcj
 * @date 2023-06-14
 */
@Controller
@RequestMapping("/manage")
@Slf4j
public class IndexController extends JSONController {

    @Autowired
    private IFastUsersService fastUsersService;

    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private ISysStatucInfo sysStatucInfo;

    /**
     * 用户登录
    */
    @RequestMapping(value="login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String userName,String passWord){
        log.info("用户登录，用户名:{},用户密码：{}",userName,passWord);
        // 调用service
        FastUsers user = fastUsersService.findFastUsersByUserName(userName);
        if(user!=null){
            // 验证密码是否正确
            if(user.getPassWord().equals(passWord)){
                // 生成token 只有登录会用，验证身份的
                String token = IDUtils.makeIDByUUID();
                // 把token存入缓存
                cacheHandle.addUsersCache(token,user);
                return success("登录成功",token);
            }else{
                return error("你输入的密码不对");
            }

        }else{
            return error("你输入的用户名不存在");
        }
    }

    /**
     * 获取统计信息
     */
    @RequestMapping("/static")
    @ResponseBody
    public String getStaticInfo(){
        // 统计当前系统累计收到的快件数量
        Integer totalNum = sysStatucInfo.getCollectPackTotal();
        // 统计当前公司营业网点数目
        Integer pointNum = sysStatucInfo.getServicePointTotal();
        // 统计已成功签收的快件数量
        Integer gainNum = sysStatucInfo.getSuccessGainPackTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("totalNum",totalNum);
        map.put("pointNum",pointNum);
        map.put("gainNum",gainNum);
        return success(map);
    }

    /**
     * 登录用户信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String token){
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        return success(user);
    }

    /**
     * 用户登出
    */
    @RequestMapping("/exit")
    @ResponseBody
    public String logout(String token){
        log.info("用户退出快递物流系统");
        cacheHandle.removeUsersCache(token);
        return success();
    }
    /**
     * 检查用户密码
    */
    @RequestMapping("/checkPwd")
    @ResponseBody
    public String checkPwd(String token,String oldPwd){
        // 打印密码信息
        log.info("旧密码,{}",oldPwd);
        // 根据token获取当前用户对象
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        // 比较老密码
        if(user.getPassWord().equals(oldPwd)){
            // 老密码正确，才能修改密码
            return success(true);
        }else{
            return success(false);
        }
    }
    /**
     * 修改登录用户密码
     */
    @RequestMapping(value="updPwd",method = RequestMethod.POST)
    @ResponseBody
    public String updPwd(String token,String newPwd){
        // 打印密码信息
        log.info("新密码,{}",newPwd);
        // 根据token获取当前用户对象
        FastUsers user = cacheHandle.getUsersInfoCache(token);
        // 重写赋值密码
        user.setPassWord(newPwd);
        fastUsersService.update(user);
        // 重新再把新的user对象存入缓存中
        cacheHandle.addUsersCache(token,user);
        return success("修改密码成功");
    }

    /**
     * 修改登录个人用户信息
     */
    @RequestMapping(value="updInfo",method = RequestMethod.POST)
    @ResponseBody
    public String updInfo(String token,FastUsers user){
        // 打印密码信息
        log.info("新用户信息,{}",user);
        fastUsersService.update(user);
        // 重新再把新的user对象存入缓存中
        cacheHandle.addUsersCache(token,user);
        return success("修改个人信息成功");
    }
}
