package com.ruoyi.web.controller.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.mock.MocksysMappingInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IMocksysMappingInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 映射信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-01
 */
@RestController
@RequestMapping("/mock/mapping")
public class MocksysMappingInfoController extends BaseController
{
    @Autowired
    private IMocksysMappingInfoService mocksysMappingInfoService;

    /**
     * 查询映射信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MocksysMappingInfo mocksysMappingInfo)
    {
        startPage();
        List<MocksysMappingInfo> list = mocksysMappingInfoService.selectMocksysMappingInfoList(mocksysMappingInfo);
        return getDataTable(list);
    }

    /**
     * 导出映射信息列表
     */
    @Log(title = "映射信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MocksysMappingInfo mocksysMappingInfo)
    {
        List<MocksysMappingInfo> list = mocksysMappingInfoService.selectMocksysMappingInfoList(mocksysMappingInfo);
        ExcelUtil<MocksysMappingInfo> util = new ExcelUtil<MocksysMappingInfo>(MocksysMappingInfo.class);
        util.exportExcel(response, list, "映射信息数据");
    }

    /**
     * 获取映射信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(mocksysMappingInfoService.selectMocksysMappingInfoById(id));
    }

    /**
     * 新增映射信息
     */
    @Log(title = "映射信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody List<MocksysMappingInfo> list)
    {
        return toAjax(mocksysMappingInfoService.insertForList(list));
    }

    /**
     * 修改映射信息
     */
    @Log(title = "映射信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MocksysMappingInfo mocksysMappingInfo)
    {
        return toAjax(mocksysMappingInfoService.updateMocksysMappingInfo(mocksysMappingInfo));
    }

    /**
     * 删除映射信息
     */
    @Log(title = "映射信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable String id)
    {
        return toAjax(mocksysMappingInfoService.deleteMocksysMappingInfoById(Integer.valueOf(id)));
    }
}
