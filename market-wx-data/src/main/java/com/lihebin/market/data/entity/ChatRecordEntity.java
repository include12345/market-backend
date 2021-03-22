package com.lihebin.market.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by lihebin on 2020/6/30.
 */
@Document(collection = "chat_record")
public class ChatRecordEntity {

    /**
     * Un read status
     */
    @Transient
    public static final Integer UN_READ = 0;

    /**
     * Readed status
     */
    @Transient
    public static final Integer READED = 1;

    @Id
    @Getter
    @Setter
    private String messageId;

    @Field("from")
    @Getter
    @Setter
    private String from;

    @Field("to")
    @Getter
    @Setter
    private String to;

    @Field("content")
    @Getter
    @Setter
    private String content;

    @Field("ctime")
    @Getter
    @Setter
    private Long ctime;

    @Field("status")
    @Getter
    @Setter
    private Integer status;
}
