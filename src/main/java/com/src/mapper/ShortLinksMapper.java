package com.src.mapper;

import com.src.model.ShortLinks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 短网址 Mapper 接口
 * </p>
 *
 * @author 陌君颜
 * @since 2023-07-25
 */
@Mapper
public interface ShortLinksMapper extends BaseMapper<ShortLinks> {

}
