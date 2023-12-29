package com.lsh.vivo.event.user.bean;

import com.lsh.vivo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 查询用户信息
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/10/1 21:20
 */
@Setter
@Getter
public class UserSaveEvent extends ApplicationEvent {

    private User user;

    public UserSaveEvent(Object source) {
        super(source);
    }

}
