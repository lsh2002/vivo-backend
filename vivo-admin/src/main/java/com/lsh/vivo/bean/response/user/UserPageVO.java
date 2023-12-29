package com.lsh.vivo.bean.response.user;

import com.lsh.vivo.bean.response.UserVO;
import com.lsh.vivo.bean.response.system.BasePageVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户分页条件
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/24 19:20
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@Schema(description = "用户集信息", name = "用户集信息")
public class UserPageVO extends BasePageVO<UserVO> {

}
