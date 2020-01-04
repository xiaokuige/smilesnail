package org.smilesnail.hoa.services.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: xiaokuige
 * @Emial: 651023907
 * @Date： 2020/1/2 15:37
 */
public class SpringMvcController {
    private static final SpringMvcController INSTANCE = new SpringMvcController();
    public static SpringMvcController getInstance() {
        return INSTANCE;
    }
    private List<Object> controllerObjectList;
    public SpringMvcController() {
        this.controllerObjectList = initController();
    }

    public List<Object> getControllerObjectList() {
        return controllerObjectList;
    }

    private List<Object> initController() {
        final List<Object> controllerList = new ArrayList<>();
        controllerList.add(new AppController());
        return controllerList;
    }
}
