package ${package}.mapper;

import ${package}.entity.${className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${className}Mapper
 *
 * @author ${author}
 * @date ${date}
 */
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {

    /**
     * 带判断的修改
     *
     * @param entity
     */
    int _update(${className} entity);

    /**
     * 不带判断的修改
     *
     * @param entity
     */
    int _updateById(${className} entity);
}
