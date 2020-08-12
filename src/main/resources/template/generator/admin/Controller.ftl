package ${package}.controller;

import com.i.lubov.comps.BatchResult;
import com.i.lubov.comps.Pager;
import com.i.lubov.comps.R;
import com.i.lubov.comps.ValidList;
import com.i.lubov.util.BeanExtUtils;
import com.i.lubov.util.WrapperUtils;
import ${package}.api.${className}Api;
import ${package}.dto.${className}DTO;
import ${package}.dto.criteria.${className}CriteriaQuery;
import ${package}.entity.${className};
import ${package}.service.${className}Service;
import ${package}.vo.${className}VO;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * ${tableComment!''}管理
 *
 * @author ${author}
 * @date ${date}
 */
@Api(tags = "/${serviceName!''}/${tableComment!''}")
@RestController
@RequestMapping("/${moduleName}/${hyphenTableName}")
public class ${className}Controller implements ${className}Api {

    @Autowired
    private ${className}Service ${camelCaseClassName}Service;

    @ApiOperation("创建${tableComment}")
    @PreAuthorize("hasAuthority('${hyphenTableName}:create')")
    @PostMapping
    @Override
    public R<Long> create(@Validated(${className}DTO.Create.class) @RequestBody ${className}DTO ${camelCaseClassName}DTO) {
        ${className} ${camelCaseClassName}Entity = BeanUtil.toBean(${camelCaseClassName}DTO, ${className}.class);
        if (${camelCaseClassName}Service.save(${camelCaseClassName}Entity)) {
            return R.<Long>ok().data(${camelCaseClassName}Entity.getId());
        }
        return R.error();
    }

    @ApiOperation("获取单个${tableComment}")
    @PreAuthorize("hasAuthority('${hyphenTableName}:retrieve')")
    @GetMapping("/{id}")
    @Override
    public R<${className}VO> get(@PathVariable("id") @ApiParam(value = "${className} 主键", required = true) Long id) {
        ${className} ${camelCaseClassName} = ${camelCaseClassName}Service.getById(id);
        if (${camelCaseClassName} != null) {
            return R.<${className}VO>ok().data(BeanUtil.toBean(${camelCaseClassName}, ${className}VO.class));
        }
    return R.ok();
    }

    @ApiOperation("分页获取${tableComment}列表")
    @PreAuthorize("hasAuthority('${hyphenTableName}:retrieve')")
    @GetMapping
    @Override
    public R<List<${className}VO>> search(@Validated ${className}CriteriaQuery ${camelCaseClassName}CriteriaQuery) {
        IPage page = ${camelCaseClassName}Service.page(WrapperUtils.buildPage(${className}.class, ${camelCaseClassName}CriteriaQuery),
        WrapperUtils.buildWrapper(${className}.class, ${camelCaseClassName}CriteriaQuery));
        return R.<List<${className}VO>>ok().total(page.getTotal())
            .data(BeanExtUtils.toBeans(page.getRecords(), ${className}VO.class));
    }

    @ApiOperation("更新单个${tableComment}")
    @PreAuthorize("hasAuthority('${hyphenTableName}:update')")
    @PutMapping("/{id}")
    @Override
    public R<Integer> update(@PathVariable("id") @ApiParam(value = "${className} 主键", required = true) Long id, @Validated(${className}DTO.Update.class) @RequestBody ${className}DTO ${camelCaseClassName}DTO) {
        ${camelCaseClassName}DTO.setId(id);
        if (${camelCaseClassName}Service.updateById(BeanUtil.toBean(${camelCaseClassName}DTO, ${className}.class))) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("批量更新${tableComment}")
    @PreAuthorize("hasAuthority('${hyphenTableName}:update')")
    @PutMapping
    @Override
    public R<List<BatchResult>> update(@Validated(${className}DTO.Update.class) @RequestBody ValidList<${className}DTO> ${camelCaseClassName}DTOs) {
        if (${camelCaseClassName}Service.updateBatchById(BeanExtUtils.toBeans(${camelCaseClassName}DTOs, ${className}.class))) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("批量删除${tableComment}")
    @PreAuthorize("hasAuthority('${hyphenTableName}:delete')")
    @DeleteMapping("/{ids}")
    @Override
    public R<List<BatchResult>> delete(@PathVariable("ids") @ApiParam(value = "${className} 主键列表,列表按逗号分割", required = true) Long[] ids) {
        if (${camelCaseClassName}Service.removeByIds(Arrays.asList(ids))) {
            return R.ok();
        }
        return R.error();
    }
}
