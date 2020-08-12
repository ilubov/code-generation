package ${package}.api;

import com.i.lubov.comps.BatchResult;
import com.i.lubov.comps.R;
import com.i.lubov.comps.ValidList;
import ${package}.dto.${className}DTO;
import ${package}.dto.criteria.${className}CriteriaQuery;
import ${package}.vo.${className}VO;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* ${tableComment}管理接口
*
* @author ${author}
* @date ${date}
*/
@RequestMapping("/${moduleName}/${hyphenTableName}")
public interface ${className}Api {

    /**
     * 创建${tableComment}
     *
     * @param ${camelCaseClassName}DTO
     * @return
     */
    @PostMapping
    R<Long> create(@Validated @RequestBody ${className}DTO ${camelCaseClassName}DTO);

    /**
     * 获取单个${tableComment}
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    R<${className}VO> get(@PathVariable("id") Long id);

    /**
     * 分页获取${tableComment}列表
     *
     * @param ${camelCaseClassName}CriteriaQuery
     * @return
     */
    @GetMapping
    R<List<${className}VO>> search(@Validated @SpringQueryMap ${className}CriteriaQuery ${camelCaseClassName}CriteriaQuery);

    /**
     * 更新单个${tableComment}
     *
     * @param id
     * @param ${camelCaseClassName}DTO
     * @return
     */
    @PutMapping("/{id}")
    R<Integer> update(@PathVariable("id") Long id, @Validated(${className}DTO.Update.class) @RequestBody ${className}DTO ${camelCaseClassName}DTO);

    /**
     * 批量更新${tableComment}
     *
     * @param ${camelCaseClassName}DTOs
     * @return
     */
    @PutMapping
    R<List<BatchResult>> update(@Validated(${className}DTO.Update.class) @RequestBody ValidList<${className}DTO> ${camelCaseClassName}DTOs);

    /**
     * 批量删除${tableComment}
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    R<List<BatchResult>> delete(@PathVariable("ids") Long[] ids);
}
