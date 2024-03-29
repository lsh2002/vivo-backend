package com.lsh.vivo.service.impl;

import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.mapper.UserAddressMapper;
import com.lsh.vivo.service.UserAddressService;
import com.lsh.vivo.service.system.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lsh.vivo.entity.table.UserAddressTableDef.USER_ADDRESS;

/**
 * @author ASUS
 * @description 针对表【user_address】的数据库操作Service实现
 * @createLocalDateTime 2023-10-28 22:52:04
 */
@Service
public class UserAddressServiceImpl extends CommonServiceImpl<UserAddressMapper, UserAddress>
        implements UserAddressService {

    @Override
    public List<UserAddress> listByUserId(String userId) {
        return queryChain()
                .where(USER_ADDRESS.USER_ID.eq(userId))
                .and(USER_ADDRESS.STATUS.ne(CommonStatusEnum.T.name()))
                .orderBy(USER_ADDRESS.STATUS.desc())
                .list();
    }

    @Override
    public void updateStatus(String userId, String id) {
        updateChain()
                .where(USER_ADDRESS.ID.eq(id))
                .set(USER_ADDRESS.IS_DEFAULT, "T")
                .update();
        updateChain()
                .where(USER_ADDRESS.USER_ID.eq(userId))
                .and(USER_ADDRESS.ID.ne(id))
                .and(USER_ADDRESS.IS_DEFAULT.ne("F"))
                .set(USER_ADDRESS.IS_DEFAULT, "F")
                .update();
    }

    @Override
    public UserAddress getByUserId(String userId) {
        return queryChain()
                .where(USER_ADDRESS.USER_ID.eq(userId))
                .and(USER_ADDRESS.STATUS.ne(CommonStatusEnum.T.name()))
                .and(USER_ADDRESS.IS_DEFAULT.eq("T"))
                .limit(1)
                .one();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserAddress entity) {
        super.save(entity);
        if (entity.getIsDefault().equals("T")) {
            updateChain()
                    .where(USER_ADDRESS.USER_ID.eq(entity.getUserId()))
                    .and(USER_ADDRESS.ID.ne(entity.getId()))
                    .and(USER_ADDRESS.IS_DEFAULT.eq("T"))
                    .set(USER_ADDRESS.IS_DEFAULT, "F")
                    .update();
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(UserAddress entity) {
        super.updateById(entity);
        if (entity.getIsDefault().equals("T")) {
            updateChain()
                    .where(USER_ADDRESS.USER_ID.eq(entity.getUserId()))
                    .and(USER_ADDRESS.ID.ne(entity.getId()))
                    .and(USER_ADDRESS.IS_DEFAULT.eq("T"))
                    .set(USER_ADDRESS.IS_DEFAULT, "F")
                    .update();
        }
        return true;
    }
}




