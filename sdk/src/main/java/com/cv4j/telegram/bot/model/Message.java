package com.cv4j.telegram.bot.model;

import java.io.Serializable;

/**
 * Created by tony on 2018/2/28.
 */
public class Message implements Serializable {

    private Integer message_id;
//    private User from;
    private Integer date;
//    private Chat chat;
//    private User forward_from;
//    private Chat forward_from_chat;
    private Integer forward_from_message_id;
    private String forward_signature;
    private Integer forward_date;
    private Message reply_to_message;
    private Integer edit_date;
    private String media_group_id;
    private String author_signature;
    private String text;
//    private MessageEntity[] entities;
//    private MessageEntity[] caption_entities;
//    private Audio audio;
//    private Document document;
//    private Game game;
//    private PhotoSize[] photo;
//    private Sticker sticker;
//    private Video video;
//    private Voice voice;
//    private VideoNote video_note;
    private String caption;
//    private Contact contact;
//    private Location location;
//    private Venue venue;
//    private User new_chat_member;
//    private User[] new_chat_members;
//    private User left_chat_member;
    private String new_chat_title;
//    private PhotoSize[] new_chat_photo;
    private Boolean delete_chat_photo;
    private Boolean group_chat_created;
    private Boolean supergroup_chat_created;
    private Boolean channel_chat_created;
    private Long migrate_to_chat_id;
    private Long migrate_from_chat_id;
    private Message pinned_message;
//    private Invoice invoice;
//    private SuccessfulPayment successful_payment;
    private String connected_website;
}
