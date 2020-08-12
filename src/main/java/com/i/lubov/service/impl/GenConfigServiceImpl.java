package com.i.lubov.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.i.lubov.entity.GenConfig;
import com.i.lubov.mapper.GenConfigMapper;
import com.i.lubov.service.GenConfigService;
import org.springframework.stereotype.Service;

@Service
public class GenConfigServiceImpl extends ServiceImpl<GenConfigMapper, GenConfig> implements GenConfigService {
    public GenConfigServiceImpl() {
    }
}
