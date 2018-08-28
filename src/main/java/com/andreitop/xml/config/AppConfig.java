package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@ComponentScan(basePackages = "com.andreitop.xml")
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {

    @Autowired
    Mount frostWolf;

    @Autowired
    Mount shadowTiger;

    @Bean(name = "frostWolf")
    public Mount frostWolf() {
        return new Wolf();
    }

    @Bean(name = "shadowTiger")
    public Mount shadowTiger() {
        return new Tiger();
    }

    @Bean(name = "trollMountMap")
    public Map<String, Mount> trollMountMap() {
        Map<String, Mount> trollMountMap = new LinkedHashMap<>();
        trollMountMap.put("m1", frostWolf);
        trollMountMap.put("m2", shadowTiger);
        return trollMountMap;
    }

    @Bean(name = "trollMountSet")
    public Set<Mount> trollMountSet() {
        Set<Mount> trollMountSet = new LinkedHashSet<>();
        trollMountSet.add(shadowTiger);
        trollMountSet.add(frostWolf);
        return trollMountSet;
    }

    @Bean(name = "dateFormatter")
    public SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("dd/mm/yyyy");
    }

    @Bean(name = "trall")
    public Unit trall() {
        Orc orc = new Orc(frostWolf);
        orc.setColorCode(9);
        orc.setWeapon("furryAxe");
        return orc;
    }

    @Bean(name = "knight")
    public Unit knight() {
        return new Human(shadowTiger, "thunderFury", "soulBlade");
    }

    @Value("${character.created}")
    private String CHARACTER_CREATION_DATE;

    @Bean (name = "zulJin")
    public Unit zulJin() throws ParseException {
        Troll zulJin = new Troll();
        zulJin.setColorCode(ThreadLocalRandom.current().nextInt(1,10));
        zulJin.setCreationDate(dateFormatter().parse(CHARACTER_CREATION_DATE));
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger));
        zulJin.setMapOfMounts(trollMountMap());
        zulJin.setSetOfMounts(trollMountSet());
        return zulJin;
    }
}
