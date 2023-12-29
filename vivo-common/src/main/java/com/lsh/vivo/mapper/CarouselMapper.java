package com.lsh.vivo.mapper;

import com.lsh.vivo.entity.Carousel;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【carousel】的数据库操作Mapper
 * @createLocalDateTime 2023-10-29 21:02:48
 * @Entity com.lsh.vivo.pojo.entity.Carousel
 */
@Mapper
public interface CarouselMapper extends BaseMapper<Carousel>, CommonMapper<Carousel> {

}




