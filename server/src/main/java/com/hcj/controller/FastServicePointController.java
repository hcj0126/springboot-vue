package com.hcj.controller;

import com.hcj.comm.JSONController;
import com.hcj.comm.PageInfo;
import com.hcj.entity.FastServicePoint;
import com.hcj.service.IFastServicePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * FastServicePointController
 *
 * @author hcj
 * @date 2023-06-15
 */

@Controller
@RequestMapping("/manage/fastServicePoint")
@Slf4j
public class FastServicePointController extends JSONController {

    @Autowired
    private IFastServicePointService fastServicePointService;

    @RequestMapping("")
    public String index() {
        return "pages/fastServicePoint";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String findAll() {
        List<FastServicePoint> list = fastServicePointService.findFastServicePointAll();
        log.info("网点信息，{}", list);
        return success(list);
    }

    /**
     * 分页获取网点信息
     */
    @RequestMapping("/page")
    @ResponseBody
    public String findPageInfo(Long pageIndex,Long pageSize,String name){
        log.info("分页查询网点信息的，当前页面:{}，每页条数:{}，搜索条件:{}",pageIndex,pageSize,name);
        PageInfo pageInfo = fastServicePointService.findFastServicePointByPage(pageIndex,pageSize,name);
        log.info("分页信息：{}",pageInfo);
        return success(pageInfo);
    }

    /**
     * 添加网点信息
     */
    @RequestMapping(value="add",method = RequestMethod.POST)
    @ResponseBody
    public String addFastServicePointInfo(FastServicePoint fastServicePoint){
        log.info("添加网点信息：{}",fastServicePoint);
        fastServicePointService.add(fastServicePoint);
        return success("添加网点信息成功");
    }
    /**
     * 修改网点信息
    */
    @RequestMapping(value="upd",method = RequestMethod.POST)
    @ResponseBody
    public String updFastServicePointInfo(FastServicePoint fastServicePoint){
        fastServicePointService.update(fastServicePoint);
        return success("修改网点信息成功");
    }

    /**
     * 删除网点信息
     */
    @RequestMapping(value="del",method = RequestMethod.POST)
    @ResponseBody
    public String delPointById(String id){
        // 先根据id查询出FastServicePoint对象
        FastServicePoint fastServicePoint = fastServicePointService.getOne(id);
        // 根据FastServicePoint对象删除
        fastServicePointService.delete(fastServicePoint);
        return success("网点信息删除成功");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
