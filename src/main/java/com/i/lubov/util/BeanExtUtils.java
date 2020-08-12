package com.i.lubov.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class BeanExtUtils {
    public BeanExtUtils() {
    }

    public static <T> List<T> toBeans(List<?> sources, Class<T> clazz) {
        List<T> target = Lists.newArrayListWithCapacity(sources.size());
        sources.forEach((source) -> target.add(BeanUtil.toBean(source, clazz)));
        return target;
    }

    public static <S, T> List<T> fillBeans(List<S> sources, Class<T> clazz, String mappingString, List<?>... params) {
        List<T> newTargets = Lists.newArrayList();
        List<String> fieldNames = Lists.newArrayList();
        List<String[]> fieldMappings = Lists.newArrayList();
        String[] mappingArray = mappingString.split(" ");
        String[] sourceFieldNames = mappingArray;
        int var9 = mappingArray.length;

        String targetFieldValues;
        for (int var10 = 0; var10 < var9; ++var10) {
            targetFieldValues = sourceFieldNames[var10];
            fieldNames.add(targetFieldValues.split("=")[0]);
            fieldMappings.add(targetFieldValues.split("=")[1].split(":"));
        }

        sourceFieldNames = null;
        String[] fieldNameMapping = null;
        Object sourceFieldValue = null;
        targetFieldValues = null;
        T newTarget = null;

        for (Iterator var13 = sources.iterator(); var13.hasNext(); newTarget = null) {
            S source = (S) var13.next();

            for (int i = 0; i < fieldNames.size(); ++i) {
                String sourceFieldName = (String) fieldNames.get(i);
                fieldNameMapping = (String[]) fieldMappings.get(i);
                Iterator var16 = params[i].iterator();

                while (var16.hasNext()) {
                    Object target = var16.next();
                    sourceFieldValue = ReflectUtil.getFieldValue(source, fieldNameMapping[0]);
                    Object targetFieldValue = ReflectUtil.getFieldValue(target, fieldNameMapping[1]);
                    if (Objects.equals(sourceFieldValue, targetFieldValue)) {
                        if (newTarget == null) {
                            newTarget = BeanUtil.toBean(source, clazz);
                        }

                        ReflectUtil.setFieldValue(newTarget, sourceFieldName, target);
                    }
                }
            }

            if (newTarget == null) {
                newTarget = BeanUtil.toBean(source, clazz);
            }

            newTargets.add(newTarget);
        }

        return newTargets;
    }
}

