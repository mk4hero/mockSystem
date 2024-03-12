package com.ruoyi.web.controller.mock;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.mock.MocksysTemplateNodeInfo;
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
import com.ruoyi.system.service.IMocksysTemplateNodeInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报文模板数据Controller
 * 
 * @author ruoyi
 * @date 2024-03-02
 */
@RestController
@RequestMapping("/mock/info")
public class MocksysTemplateNodeInfoController extends BaseController
{
    @Autowired
    private IMocksysTemplateNodeInfoService mocksysTemplateNodeInfoService;

    /**
     * 查询报文模板数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        startPage();
        List<MocksysTemplateNodeInfo> list = mocksysTemplateNodeInfoService.selectMocksysTemplateNodeInfoList(mocksysTemplateNodeInfo);
        return getDataTable(list);
    }

    /**
     * 导出报文模板数据列表
     */
    @Log(title = "报文模板数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        List<MocksysTemplateNodeInfo> list = mocksysTemplateNodeInfoService.selectMocksysTemplateNodeInfoList(mocksysTemplateNodeInfo);
        ExcelUtil<MocksysTemplateNodeInfo> util = new ExcelUtil<MocksysTemplateNodeInfo>(MocksysTemplateNodeInfo.class);
        util.exportExcel(response, list, "报文模板数据数据");
    }

    /**
     * 获取报文模板数据详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(mocksysTemplateNodeInfoService.selectMocksysTemplateNodeInfoById(id));
    }

    /**
     * 新增报文模板数据
     */
    @Log(title = "报文模板数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        return toAjax(mocksysTemplateNodeInfoService.insertMocksysTemplateNodeInfo(mocksysTemplateNodeInfo));
    }

    /**
     * 修改报文模板数据
     */
    @Log(title = "报文模板数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MocksysTemplateNodeInfo mocksysTemplateNodeInfo)
    {
        return toAjax(mocksysTemplateNodeInfoService.updateMocksysTemplateNodeInfo(mocksysTemplateNodeInfo));
    }

    /**
     * 删除报文模板数据
     */
    @Log(title = "报文模板数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(mocksysTemplateNodeInfoService.deleteMocksysTemplateNodeInfoByIds(ids));
    }
}
