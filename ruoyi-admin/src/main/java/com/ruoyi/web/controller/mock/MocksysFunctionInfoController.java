package com.ruoyi.web.controller.mock;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.mock.MocksysFunctionInfo;
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
import com.ruoyi.system.service.IMocksysFunctionInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报文模板函数数据Controller
 * 
 * @author ruoyi
 * @date 2024-03-08
 */
@RestController
@RequestMapping("/system/info")
public class MocksysFunctionInfoController extends BaseController
{
    @Autowired
    private IMocksysFunctionInfoService mocksysFunctionInfoService;

    /**
     * 查询报文模板函数数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MocksysFunctionInfo mocksysFunctionInfo)
    {
        startPage();
        List<MocksysFunctionInfo> list = mocksysFunctionInfoService.selectMocksysFunctionInfoList(mocksysFunctionInfo);
        return getDataTable(list);
    }

    /**
     * 导出报文模板函数数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "报文模板函数数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MocksysFunctionInfo mocksysFunctionInfo)
    {
        List<MocksysFunctionInfo> list = mocksysFunctionInfoService.selectMocksysFunctionInfoList(mocksysFunctionInfo);
        ExcelUtil<MocksysFunctionInfo> util = new ExcelUtil<MocksysFunctionInfo>(MocksysFunctionInfo.class);
        util.exportExcel(response, list, "报文模板函数数据数据");
    }

    /**
     * 获取报文模板函数数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(mocksysFunctionInfoService.selectMocksysFunctionInfoById(id));
    }

    /**
     * 新增报文模板函数数据
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "报文模板函数数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MocksysFunctionInfo mocksysFunctionInfo)
    {
        return toAjax(mocksysFunctionInfoService.insertMocksysFunctionInfo(mocksysFunctionInfo));
    }

    /**
     * 修改报文模板函数数据
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "报文模板函数数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MocksysFunctionInfo mocksysFunctionInfo)
    {
        return toAjax(mocksysFunctionInfoService.updateMocksysFunctionInfo(mocksysFunctionInfo));
    }

    /**
     * 删除报文模板函数数据
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "报文模板函数数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(mocksysFunctionInfoService.deleteMocksysFunctionInfoByIds(ids));
    }
}
