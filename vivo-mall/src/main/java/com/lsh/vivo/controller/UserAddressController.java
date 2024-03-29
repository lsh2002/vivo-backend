package com.lsh.vivo.controller;

import com.lsh.vivo.bean.request.address.AddressSaveVO;
import com.lsh.vivo.bean.request.address.AddressStatusVO;
import com.lsh.vivo.bean.request.address.AddressUpdateVO;
import com.lsh.vivo.bean.response.address.UserAddressVO;
import com.lsh.vivo.entity.UserAddress;
import com.lsh.vivo.mapper.struct.UserAddressMpp;
import com.lsh.vivo.service.UserAddressService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lsh
 * @since 2023-09-09 15:43
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping
    private List<UserAddressVO> listByUserId(@NotNull String userId) {
        List<UserAddress> userAddresses = userAddressService.listByUserId(userId);
        return UserAddressMpp.INSTANCE.toVO(userAddresses);
    }

    @GetMapping("/default")
    private UserAddressVO getByUserId(@NotNull String userId) {
        UserAddress userAddresses = userAddressService.getByUserId(userId);
        return UserAddressMpp.INSTANCE.toVO(userAddresses);
    }

    @PutMapping("/updateStatus")
    public void updateStatus(@RequestBody @NotNull AddressStatusVO addressStatusVO) {
        userAddressService.updateStatus(addressStatusVO.getUserId(), addressStatusVO.getId());
    }

    @PutMapping
    public void update(@RequestBody @NotNull AddressUpdateVO addressUpdateVO) {
        UserAddress address = UserAddressMpp.INSTANCE.toDO(addressUpdateVO);
        userAddressService.updateById(address);
    }

    @PostMapping
    public void save(@NotNull @RequestBody AddressSaveVO addressSaveVO) {
        UserAddress userAddress = UserAddressMpp.INSTANCE.toDO(addressSaveVO);
        userAddressService.save(userAddress);
    }

    @DeleteMapping("/delete/{id}")
    public void remove(@NotNull @PathVariable String id) {
        userAddressService.removeById(id);
    }
}
