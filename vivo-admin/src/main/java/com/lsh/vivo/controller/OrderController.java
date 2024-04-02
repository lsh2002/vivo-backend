package com.lsh.vivo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.lsh.vivo.bean.dto.order.OrderConditionDTO;
import com.lsh.vivo.bean.request.order.OrderConditionVO;
import com.lsh.vivo.bean.request.order.OrderUpdateVO;
import com.lsh.vivo.bean.response.order.OrderVO;
import com.lsh.vivo.bean.response.system.PageVO;
import com.lsh.vivo.entity.Order;
import com.lsh.vivo.mapper.struct.OrderMpp;
import com.lsh.vivo.service.OrderService;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单列表模块控制器
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 0:22
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 模块前缀
     */
    private static final String MODULE_PREFIX = "order:";

    @Operation(summary = "查询某个商品信息", description = "授权限控制，order:detail - 查看单条记录权限, order:* - 商品模块全部权限")
    @ApiOperationSupport(order = 1)
    @PreAuthorize("hasAuthority('order:view') || hasAuthority('order:*')")
    @GetMapping("/{id}")
    public OrderVO getById(@NotNull @Valid @PathVariable("id") String id) {
        Order order = orderService.getById(id);
        return OrderMpp.INSTANCE.toVO(order);
    }

    @Operation(summary = "获取模块下当前用户有的权限", description = "获取模块下当前用户有的权限,无授权限制")
    @ApiOperationSupport(order = 5)
    @GetMapping("/per")
    public List<String> getPermissions() {
        return orderService.listPermissions(MODULE_PREFIX);
    }

    @Operation(summary = "查询商品列表信息", description = "授权限控制，order:view - 查询商品列表权限, order:* - 商品模块全部权限")
    @ApiOperationSupport(order = 5)
    @PreAuthorize("hasAuthority('order:view') || hasAuthority('order:*')")
    @GetMapping
    public PageVO<OrderVO> listPageable(@NotNull OrderConditionVO condition) {
        OrderConditionDTO orderConditionDTO = OrderMpp.INSTANCE.toDTO(condition);
        Page<Order> page = new Page<>(condition.getPage(), condition.getSize());
        Page<Order> orderPage = orderService.page(page, orderConditionDTO);
        return OrderMpp.INSTANCE.toPageVO(orderPage);
    }

    @Operation(summary = "修改", description = "授权限控制，order:update - 查看单条记录权限, order:* - 商品模块全部权限")
    @ApiOperationSupport(order = 10)
    @PreAuthorize("hasAuthority('order:update') || hasAuthority('order:*')")
    @PutMapping
    public void updateById(@NotNull @RequestBody OrderUpdateVO orderUpdateVO) {
        Order newOrder = OrderMpp.INSTANCE.toDO(orderUpdateVO);
        orderService.updateById(newOrder);
    }

    @GetMapping("/today")
    public JSONObject getTodayData() {
        return orderService.getTodayData();
    }

    @GetMapping("/month")
    public JSONObject getMonthData() {
        return orderService.getMonthData();
    }

    @GetMapping("/year")
    public JSONObject getYearData() {
        return orderService.getYearData();
    }
}
