package com.example.proxy.adapter;

import com.example.proxy.model.SevenEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NERProxyTest {
    @Autowired
    private NERProxy ner;

    @Disabled("need setting text analysis service url")
    @Test
    public void testSevenEntity() {
        SevenEntity entity = ner.ner("针对水安全，台达采用效率提升与调适双轨并重的方式，导入完善的水资源风险评估流程，持续强化预警模式，打造设施韧性与强化水资源，以降低营运中断风险，有效管理及提升因应风险的能力。 2020年台达以桃园厂为示范基地，优先建立「台达水风险评估系统」，以厂区可接受的各项用水门槛做为风险评估依据，同时针对该地区未来发展与自身成长，进行缺水风险的情境模拟与推估，透过回收水提升及备援水源调度措施等，强化区域水资源韧性，2021年亦将扩大导入全台厂区，达到永续用水目的。");
        System.out.println(entity);
    }
}
