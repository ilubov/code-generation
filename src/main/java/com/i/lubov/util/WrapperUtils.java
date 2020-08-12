package com.i.lubov.util;

import com.i.lubov.annotation.Query;
import com.i.lubov.annotation.Query.FieldType;
import com.i.lubov.exception.BadRequestException;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class WrapperUtils {

    static long START_PAGE_NO = 1L;
    static long MAX_PAGE_SIZE = 500L;

    public WrapperUtils() {
    }

    public static <E, C> IPage<E> buildPage(Class<E> clazz, C criteria) {
        Object currentObject = ReflectUtil.getFieldValue(criteria, "page");
        Object sizeObject = ReflectUtil.getFieldValue(criteria, "size");
        long current = currentObject == null ? START_PAGE_NO : (Long) currentObject;
        long size = sizeObject == null ? MAX_PAGE_SIZE : (Long) sizeObject;
        if (size > MAX_PAGE_SIZE) {
            size = MAX_PAGE_SIZE;
        }

        IPage<E> page = new Page(current, size);
        return page;
    }

    public static <T, C> Wrapper buildWrapper(Class<T> clazz, C criteria) throws BadRequestException {
        try {
            QueryWrapper<T> wrapper = new QueryWrapper();
            buildBlurry(wrapper, criteria);
            buildSort(wrapper, criteria);
            buildGroup(wrapper, criteria);
            String fieldName = null;
            Object fieldValue = null;
            Query query = null;
            Field[] fields = ReflectUtil.getFields(criteria.getClass());
            Field[] var7 = fields;
            int var8 = fields.length;

            for (int var9 = 0; var9 < var8; ++var9) {
                Field field = var7[var9];
                fieldName = field.getName();
                fieldValue = ReflectUtil.getFieldValue(criteria, field);
                if (!(fieldValue instanceof String) || !"".equals(fieldValue)) {
                    query = (Query) field.getAnnotation(Query.class);
                    if (fieldValue != null && query != null && !"blurry".equals(fieldName) && !query.sort() && !query.group()) {
                        buildType(wrapper, query, StrUtil.isNotEmpty(query.column()) ? query.column() : fieldName, fieldValue);
                    }
                }
            }

            return wrapper;
        } catch (Exception var11) {
            throw new BadRequestException("SQL查询条件解析错误");
        }
    }

    private static <T, C> void buildBlurry(QueryWrapper<T> wrapper, C criteria) {
        Object fieldValue = ReflectUtil.getFieldValue(criteria, "blurry");
        if (!Objects.isNull(fieldValue) && fieldValue instanceof String) {
            Query query = ReflectUtil.getField(criteria.getClass(), "blurry").getAnnotation(Query.class);
            String blurry = query.blurry();
            String[] columns = blurry.split(",");
            String[] var6 = columns;
            int var7 = columns.length;

            for (int var8 = 0; var8 < var7; ++var8) {
                String column = var6[var8];
                wrapper.or();
                wrapper.like(column, fieldValue);
            }
        }

    }

    private static <T, C> void buildSort(QueryWrapper<T> wrapper, C criteria) {
        Object fieldValue = ReflectUtil.getFieldValue(criteria, "sort");
        if (fieldValue instanceof String && StrUtil.isNotEmpty((String) fieldValue)) {
            String sorts = (String) fieldValue;
            String[] columns = sorts.split(",");
            getAscColumns(columns).forEach((c) -> wrapper.orderByAsc(c));
            getDescColumns(columns).forEach((c) -> wrapper.orderByDesc(c));
        }

    }

    private static List<String> getAscColumns(String[] columns) {
        return Arrays.stream(columns).filter((column) ->
                "asc".equals(column.split(":")[1])
        ).map((column) ->
                StrUtil.toUnderlineCase(column.split(":")[0])
        ).collect(Collectors.toList());
    }

    private static List<String> getDescColumns(String[] columns) {
        return Arrays.stream(columns).filter((column) ->
                "desc".equals(column.split(":")[1])
        ).map((column) ->
                StrUtil.toUnderlineCase(column.split(":")[0])
        ).collect(Collectors.toList());
    }

    private static <T, C> void buildGroup(QueryWrapper<T> wrapper, C criteria) {
        Object fieldValue = ReflectUtil.getFieldValue(criteria, "group");
        if (fieldValue instanceof String && StrUtil.isNotEmpty((String) fieldValue)) {
            String groups = (String) fieldValue;
            wrapper.groupBy(groups.split(","));
        }

    }

    private static <T> void buildType(QueryWrapper<T> wrapper, Query query, String column, Object fieldValue) {
        if (!Objects.isNull(fieldValue)) {
            switch (query.type()) {
                case EQ:
                    wrapper.eq(column, fieldValue);
                    break;
                case NE:
                    wrapper.ne(column, fieldValue);
                    break;
                case GT:
                    wrapper.gt(column, fieldValue);
                    break;
                case GE:
                    wrapper.ge(column, fieldValue);
                    break;
                case LT:
                    wrapper.lt(column, fieldValue);
                    break;
                case LE:
                    wrapper.le(column, fieldValue);
                    break;
                case LIKE:
                    wrapper.like(column, fieldValue);
                    break;
                case LIKE_LEFT:
                    wrapper.likeLeft(column, fieldValue);
                    break;
                case LIKE_RIGHT:
                    wrapper.likeRight(column, fieldValue);
                    break;
                case IS_NULL:
                    wrapper.isNull(column);
                    break;
                case IS_NOT_NULL:
                    wrapper.isNotNull(column);
                    break;
                case IN:
                    if (query.fieldType() == FieldType.NUMBER) {
                        wrapper.inSql(column, String.valueOf(fieldValue));
                    } else {
                        wrapper.in(column, String.valueOf(fieldValue).split(","));
                    }
                    break;
                case NOT_IN:
                    if (query.fieldType() == FieldType.NUMBER) {
                        wrapper.notInSql(column, String.valueOf(fieldValue));
                    } else {
                        wrapper.notIn(column, String.valueOf(fieldValue).split(","));
                    }
            }

        }
    }
}
