package com.atguigu.ggkt.vod.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 股吧数据
 * </p>
 *
 * @author atguigu
 * @since 2022-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GubaData implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      private Long id;

    /**
     * 阅读数
     */
    private String readNumber;

    /**
     * 评论数
     */
    private Long commentNumber;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 最后更新
     */
    private String updateTime;

    /**
     * 情绪值
     */
    private String emotionValue;


}
