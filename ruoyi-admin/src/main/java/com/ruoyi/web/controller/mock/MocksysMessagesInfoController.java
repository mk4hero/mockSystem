package com.ruoyi.web.controller.mock;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.mock.MocksysMessagesInfo;
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
import com.ruoyi.system.service.IMocksysMessagesInfoService;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 报文归档信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-01
 */
@RestController
@RequestMapping("/mock/log")
public class MocksysMessagesInfoController extends BaseController
{
    @Autowired
    private IMocksysMessagesInfoService mocksysMessagesInfoService;

    /**
     * 查询报文归档信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MocksysMessagesInfo mocksysMessagesInfo)
    {
        startPage();
        List<MocksysMessagesInfo> list = mocksysMessagesInfoService.selectMocksysMessagesInfoList(mocksysMessagesInfo);
        return getDataTable(list);
    }

    /**
     * 导出报文归档信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "报文归档信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MocksysMessagesInfo mocksysMessagesInfo)
    {
        List<MocksysMessagesInfo> list = mocksysMessagesInfoService.selectMocksysMessagesInfoList(mocksysMessagesInfo);
        ExcelUtil<MocksysMessagesInfo> util = new ExcelUtil<MocksysMessagesInfo>(MocksysMessagesInfo.class);
        util.exportExcel(response, list, "报文归档信息数据");
    }

    /**
     * 获取报文归档信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(mocksysMessagesInfoService.selectMocksysMessagesInfoById(id));
    }

    /**
     * 新增报文归档信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "报文归档信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MocksysMessagesInfo mocksysMessagesInfo)
    {
        return toAjax(mocksysMessagesInfoService.insertMocksysMessagesInfo(mocksysMessagesInfo));
    }

    /**
     * 修改报文归档信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "报文归档信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MocksysMessagesInfo mocksysMessagesInfo)
    {
        return toAjax(mocksysMessagesInfoService.updateMocksysMessagesInfo(mocksysMessagesInfo));
    }

    /**
     * 删除报文归档信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "报文归档信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(mocksysMessagesInfoService.deleteMocksysMessagesInfoByIds(ids));
    }
}
