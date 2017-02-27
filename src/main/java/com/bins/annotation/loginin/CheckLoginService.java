package com.bins.annotation.loginin;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

/**
 * Created by songrongbin on 2017/2/24.
 */
public class CheckLoginService {

    public void checkLogin() {
        NeedLogInEnum needLogIn = NeedLogInEnum.NO;
        try {
            DemoAnnotation demoAnnotation = new DemoAnnotation();
            needLogIn = LoggedInProcessor.needLogIn(demoAnnotation, "get", Parameters.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
