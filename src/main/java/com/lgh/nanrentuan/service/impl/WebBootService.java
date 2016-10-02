package com.lgh.nanrentuan.service.impl;

import com.lgh.nanrentuan.entity.SystemConfig;
import com.lgh.nanrentuan.model.CommonVersion;
import com.lgh.nanrentuan.repository.SystemConfigRepository;
import com.lgh.nanrentuan.service.SystemConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 启动设置
 * Created by lgh on 2016/5/5.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class WebBootService implements ApplicationListener<ContextRefreshedEvent> {

    private static Log log = LogFactory.getLog(WebBootService.class);

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Autowired
    private BaseService baseService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() != null) {
            SystemConfig systemConfig = systemConfigRepository.findOne("DatabaseVersion");
            if (systemConfig == null) {
                SystemConfig databaseVession = new SystemConfig();
                databaseVession.setCode("DatabaseVersion");
                databaseVession.setValueForCode(String.valueOf(CommonVersion.initVersion.ordinal()));
                systemConfigRepository.save(databaseVession);
            }

            //系统升级
            baseService.systemUpgrade("DatabaseVersion", CommonVersion.class, CommonVersion.Version102, (upgrade) -> {
                switch (upgrade) {
                    case Version102:
                        try {
                            initSystemConfig();
                        } catch (Exception e) {
                            log.info("upgrade to " + CommonVersion.Version102.ordinal() + " error", e);
                        }
                        break;

                    case Version101:
                        try {

                        } catch (Exception e) {
                            log.info("upgrade to " + CommonVersion.Version101.ordinal() + " error", e);
                        }
                        break;
                }
            });

        }
    }

    private void initSystemConfig() {
        List<SystemConfig> systemConfigs = new ArrayList<>();
        systemConfigs.add(new SystemConfig(SystemConfigService.title, "爱国之家", "网站标题"));
        systemConfigs.add(new SystemConfig(SystemConfigService.keywords, "爱国", "网站关键字"));
        systemConfigs.add(new SystemConfig(SystemConfigService.description, "爱国是一个国民应尽的责任", "网站描述"));
        systemConfigs.add(new SystemConfig(SystemConfigService.top, "", "顶部"));
        systemConfigRepository.save(systemConfigs);
    }
}
