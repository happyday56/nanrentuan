package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.SystemConfig;
import com.lgh.nanrentuan.model.SystemConfigModel;
import com.lgh.nanrentuan.repository.SystemConfigRepository;
import com.lgh.nanrentuan.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/2.
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    public SystemConfigModel list() {
        SystemConfigModel systemConfigModel = new SystemConfigModel();

        List<SystemConfig> systemConfigs = systemConfigRepository.findAll();

        systemConfigModel.setTitle(systemConfigs.stream().filter(x -> x.getCode().equals(SystemConfigService.title)).findFirst().get().getValueForCode());
        systemConfigModel.setKeywords(systemConfigs.stream().filter(x -> x.getCode().equals(SystemConfigService.keywords)).findFirst().get().getValueForCode());
        systemConfigModel.setDescription(systemConfigs.stream().filter(x -> x.getCode().equals(SystemConfigService.description)).findFirst().get().getValueForCode());
        systemConfigModel.setTop(systemConfigs.stream().filter(x -> x.getCode().equals(SystemConfigService.top)).findFirst().get().getValueForCode());
        return systemConfigModel;
    }

    public void save(String title, String keywords, String description, String top) {
        SystemConfig systemConfig = systemConfigRepository.findOne(SystemConfigService.title);
        systemConfig.setValueForCode(title);
        systemConfigRepository.save(systemConfig);

        systemConfig = systemConfigRepository.findOne(SystemConfigService.keywords);
        systemConfig.setValueForCode(keywords);
        systemConfigRepository.save(systemConfig);

        systemConfig = systemConfigRepository.findOne(SystemConfigService.description);
        systemConfig.setValueForCode(description);
        systemConfigRepository.save(systemConfig);

        systemConfig = systemConfigRepository.findOne(SystemConfigService.top);
        systemConfig.setValueForCode(top);
        systemConfigRepository.save(systemConfig);
    }
}
