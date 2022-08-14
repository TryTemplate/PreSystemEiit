package com.eiit.presystemeiit.common;

public interface Constants {

    /**
     * resultful
     * resut code
     */
    enum RET_CODE {
        ;
        public static final int SUCCESS = 0;
        public static final int FAIL = 1;
        public static final int ERROR = -1;
        public static final int WSQ = -10;


        public static final int WRONGFUL_TOKEN = 50008;         //非法token
        public static final int REMOTE_LOGIN = 50012;           //其他客户端登录
        public static final int TOKEN_EXPIRE = 50014;           //Token过期
    }



    enum SOCKET_CLIENT_CODE {
        ;
        public static final int TYPE_MESSAGE = 0;               //普通消息
        public static final int TYPE_COMMAND = 1;               //命令
        public static final int TYPE_DOWNLOAD = 2;              //文件下载  广告发布
        public static final int TYPE_SERIAL_NUMBER = 3;         //发送序列号


        //type=1时命令
        public static final String COMMAND_SCREENSHOT = "screenshot";                       //截图
        public static final String COMMAND_REBOOT = "reboot";                               //重启
        public static final String COMMAND_SYNCHRONIZATION_TIME = "synchronization_time";   //同步系统时间
        public static final String COMMAND_SETTING = "setting";                             //设置
        public static final String COMMAND_BOND = "bond";                                   //设备绑定通知
        public static final String COMMAND_PUBLISH_ADV_EN = "publish_adv";                  //广告发布
        public static final String COMMAND_UNDO_ADV_EN = "undo_adv";                        //撤销广告发布
        public static final String COMMAND_MODIFY_DEVICE_NAME = "modify_device_name";       //设备修改名称
        public static final String SERVER_CODE_ERROR = "server_code_error";                 //设备接入码不正确
        public static final String COMMAND_UNBOND = "unbond";                               //解绑
        public static final String COMMAND_DETAIL = "device_detail";                        //获取设备详情
        public static final String COMMAND_FORBID = "device_forbid";                        //设备禁用
        public static final String COMMAND_UNFORBID = "device_unforbid";                    //设备启用

        public static final String COMMAND_AUDIO_ADJUST = "audio_adjust";                   //设置设备声音
        public static final String COMMAND_DEVICE_LOGO = "device_logo";                     //设置设备LOGO
        public static final String COMMAND_APK_UPDATE = "apk_update";               //设置设备version

        public static final String COMMAND_DEVICE_INFO = "device_info";             //获取设备信息
        public static final String COMMAND_INPUT = "input";                         //设备已录入通知--未使用
        public static final String COMMAND_TIMEOUT = "timeout";                         //设备心跳超时

        //type=2时内容
        public static final int MODEL_ADD = 1;          //追加
        public static final int MODEL_REPLACE = 2;      //替换
        public static final int MODEL_REPLACE_NOW = 3;  //立即替换

        public static final int FILE_TYPE_ADV_PLAN = 0; //广告计划
        public static final int FILE_TYPE_RUN_LAMP = 1; //滚动字幕
        public static final int FILE_TYPE_OHR_ADV = 2;  //第三方广告
        public static final int FILE_TYPE_SHUTDOWN = 3; //开关机
        public static final int FILE_TYPE_LOGO = 4; //logo
        public static final int FILE_TYPE_MATERIAL_FILE_DOWNLOAD = 5; //文件下载


    }

    enum REDIS_KEY{
        ;
        /** @Remark 在线设备文件夹 */
        public static final String ONLINE_EQUIPMENT_FOLDER = "online_equipment";        //未使用


        /** @Remark 所有未录入设备文件夹 */
        public static final String NOT_ENTERED_EQUIPMENT_FOLDER_ALL = "not_entered_equipment_all";


        /** @Remark 截图文件夹 */
        public static final String EQUIPMENT_SCREENSHOT_FOLDER = "screenshot_folder";
        /** @Remark 重启状态文件夹 */
        public static final String EQUIPMENT_REBOOT_STATUS_FOLDER = "reboot_status_folder";
        /** @Remark 同步服务器时间文件夹 */
        public static final String EQUIPMENT_SYNCHRONIZATION_TIME_FOLDER = "synchronization_time_folder";
        /** @Remark 设置指令文件夹 */
        public static final String EQUIPMENT_SETTING_FOLDER = "setting_folder";
        /** @Remark 设备详情 */
        public static final String EQUIPMENT_DETAILS_FOLDER = "details_folder";




        /** @Remark 设置设置声音 */
        public static final String EQUIPMENT_SETTING_SOUND = "setting_folder_sound";


        //在线的设备列表
        public static final String EQUIPMENT_LIST = "equipment_list";
        public static final String EQUIPMENT_CHANNEL = "equipment_channel";

        //设备心跳时间
        public static final long EQUIPMENT_HEARTBEAT_TIME = 21L;
        //设备心跳时间测试环境
        public static final long EQUIPMENT_HEARTBEAT_TIME_FOR_TEST = 12000L;

        public static final String CAPTCHA_KEY = "login_verification_code";         /*用户验证码*/
        public static final int LOGIN_USER_TOKEN_TIMEOUT = 7200;                    /*token在redis中的有效时间, 和session时间同步*/
        public static final String LOGIN_USER_TOKEN_FOLDER = "login_user_token";    /*登录用户的token文件夹*/
    }


    enum WECAT_XCX {
        ;

        public static final String CAPTCHA_KEY = "login_verification_code_xcx";                     /*小程序用户的登录验证码*/
        public static final String REDIS_LOGIN_USER_TOKEN_FOLDER_XCX = "login_user_token_xcx";      /*小程序登录用户的token文件夹*/
        public static final int REDIS_LOGIN_USER_TOKEN_TIMEOUT_XCX = 1 * 24 * 60 * 60;                  /*小程序token在redis中的有效时间, 和session时间同步*/
        //                                                           天   时   分   秒
        ////un used
        public static final String REDIS_LOGIN_USER_FOLDER_XCX = "login_user_token_xcx";      /*小程序登录用户的登录信息文件夹*/
        public static final int REDIS_LOGIN_USER_TIMEOUT_XCX = 1 * 24 * 60 * 60;                  /*小程序登录信息在redis中的有效时间, 和session时间同步*/
        //                                                           天   时   分   秒
    }




    enum DATABASE_ADV_TYPE{
        ;
        /** @Remark 广告区域类型 V.视频 P.图片 M.音乐 C.滚动字幕 */
        public static final String DATABASE_ADV_ZONE_TYPE_PHOTO = "P";
        public static final String DATABASE_ADV_ZONE_TYPE_VIDEO = "V";
        public static final String DATABASE_ADV_ZONE_TYPE_MUSIC = "M";
        public static final String DATABASE_ADV_ZONE_TYPE_SUBTITLE = "C";
    }




    /**
     * 秘钥key
     */
    enum AES_KEY {
        ;
        /* 签发登录令牌的唯一key */
        public static final String JWT_KEY = "ekvision";
        /* MySql密码加密key */
        public static final String SQL_PASSWORD_KEY = "ekv";
    }




    /* token验证相关key */
    enum TOKEN_KEY {
        ;
        /* @token验证权限级别 */
        public static final String LEVEL_USER = "user";
        public static final String LEVEL_ADMIN = "admin";
        public static final String LEVEL_SUPERADMIN = "super_admin";
    }

    /**
     * session相关的key
     */
    enum SESSION_KEY {
        ;
        public static final String USER_SESSION = "login_user_session";
        public static final String USER_SESSION_TOKEN = "login_user_token";
        public static final String RESET_PASSWORD_SESSION_EMAIL = "reset_password_email";
        public static final String RESET_PASSWORD_SESSION_EMAIL_CODE = "reset_password_email_code";
        public static final String RESET_PASSWORD_SESSION_PHONE = "reset_password_phone";
        public static final String RESET_PASSWORD_SESSION_PHONE_CODE = "reset_password_phone_code";

        public static final String DEL_ACCOUNT_SESSION_PHONE_CODE = "del_account_phone_code";
    }

    //客户状态
    enum SQL_CUSTOMER_STATUS {
        ;
        //status` int NOT NULL DEFAULT '1' COMMENT '状态 1.正常 2.停用 3.冻结',
         public static final Integer STATUS_NORMAL = 1;             //正常
         public static final Integer STATUS_DEACTIVATE = 2;         //停用
         public static final Integer STATUS_FROZEN = 3;             //冻结

    }

    //用户账号状态
    enum SQL_USER_CODE {
        ;
         public static final Integer STATUS_NORMAL = 1;             //正常
         public static final Integer STATUS_DEACTIVATE = 2;         //停用
         public static final Integer STATUS_FROZEN = 3;             //冻结
         public static final Integer STATUS_LOGIN_LOCK = 4;         //登录异常锁定

    }

    //广告状态
    enum SQL_ADVERTISEMENT_STATUS {
        ;
        public static final Integer STATUS_WILL_REVIEWED = 1;       //待审核
        public static final Integer STATUS_WILL_RELEASED = 2;       //待发布
        public static final Integer STATUS_RELEASED_ING = 3;        //发布中
        public static final Integer STATUS_RELEASED_FINISH = 4;     //发布完成
        public static final Integer STATUS_REVOKE_ING = 5;          //撤销中
        public static final Integer STATUS_REVOKE_FINISH = 6;       //撤销完成

    }

    //滚动字幕状态
    enum SQL_ADVRUNNINGLANTERN_STATUS {
        ;
        public static final Integer STATUS_WILL_REVIEWED = 1;       //待审核
        public static final Integer STATUS_WILL_RELEASED = 2;       //待发布
        public static final Integer STATUS_RELEASED_ING = 3;        //发布中
        public static final Integer STATUS_RELEASED_FINISH = 4;     //发布完成
        public static final Integer STATUS_REVOKE_ING = 5;          //撤销中
        public static final Integer STATUS_REVOKE_FINISH = 6;       //撤销完成
        public static final Integer STATUS_OVERDUE = 7;             //过期

    }

    //广告计划状态
    enum SQL_ADV_PLAN_STATUS {;
        public static final Integer STATUS_WILL_REVIEWED = 1;       //待审核
        public static final Integer STATUS_WILL_RELEASED = 2;       //待发布
        public static final Integer PLAN_STATUS_PUSHING = 3;        //发布中
        public static final Integer PLAN_STATUS_PUSHED = 4;         //发布完成
        public static final Integer PLAN_STATUS_REVOKE_ING = 5;     //撤销中
        public static final Integer PLAN_STATUS_REVOKE_FINISH = 6;  //撤销完成
    }

    //设备发布状态 1.接收到命令 下载中/执行中 2.下载/执行完成
    enum SQL_PUBLISH_EQUIPMENT_STATUS {;
        public static final Integer EQ_STATUS_COPYED = 1;           //接收到命令 下载中/执行中
        public static final Integer EQ_STATUS_FULFIL = 2;           //下载/执行完成
    }

    //发布类型 1.发布 2.撤销
    enum SQL_PUBLISH_RECORD_TYPE_EASY {;
        public static final Integer EQ_STATUS_PUBLISH = 1;          //发布
        public static final Integer EQ_STATUS_UNDO = 2;             //撤销
    }
    enum SQL_PUBLISH_RECORD_TYPE {;
        public static final Integer EQ_STATUS_PADV = 1;             //普通广告
        public static final Integer EQ_STATUS_PDADV = 2;            //双屏广告
        public static final Integer EQ_STATUS_PRUN = 3;             //滚动字幕
        public static final Integer EQ_STATUS_PPLAN = 4;            //广告计划
        public static final Integer EQ_STATUS_RADV = 5;             //撤销普通广告
        public static final Integer EQ_STATUS_RDADV = 6;            //撤销双屏广告
        public static final Integer EQ_STATUS_RRUN = 7;             //撤销滚动字幕
        public static final Integer EQ_STATUS_RPLAN = 8;            //撤销广告计划
    }

    //设备状态 1.正常 2.禁用
    enum SQL_EQUIPMENT_STATUS {;
        public static final Integer EQ_STATUS_NORMAL = 1;           //正常
        public static final Integer EQ_STATUS_DISABLE = 2;          //禁用
    }

    //广告计划创建类型
    enum SQL_PLAN_CREATE_TYPE {
        ;
        public static final Integer PLAN_TYPE_ADV_PLAN = 0;         //广告计划创建
        public static final Integer PLAN_TYPE_ADV_PUSH = 1;         //广告发布创建
        public static final Integer PLAN_TYPE_SENIOR = 3;           //高级发布

    }


    //客户系统参数配置id对应的配置
    enum CONFIG_ID_TYPE_CODE {
        ;
        public static final Integer MATERIAL_PHOTO_SIZE = 101;      //素材图片配置
        public static final Integer MATERIAL_VIDEO_SIZE = 102;      //素材视频配置
        public static final Integer ADVERTISEMENT_REVIEWED = 111;   //广告审核

    }


    //广告类型
    enum SQL_ADV_TYPE {;
        public static final Integer TYPE_ADV_PLAN = 0;              //广告计划
        public static final Integer TYPE_ADV = 1;                   //广告
        public static final Integer TYPE_RUNNING = 2;               //滚动字幕
    }


    //系统配置编码code
    enum SQL_SYSTEM_CONFIG {;
        public static final Integer SYSTEM_ACCESS_CODE = 10001;     //服务器介入码
    }


    /**
     * RabbitMQ相关的key
     */
    enum MQ_CODE {
        ;
        //简单模式
        // 不需要交换机
        // public static final String SIMPLE_EXCHANGE = "service_technical";
        public static final String SIMPLE_EXCHANGE_QUEUE_NAME_WORK = "go_work";

        //订阅模式
        public static final String FANOUT_EXCHANGE = "service_technical_fanout";
        public static final String FANOUT_EXCHANGE_QUEUE_NAME_ONE = "fanout_queue_one";
        public static final String FANOUT_EXCHANGE_QUEUE_NAME_TWO = "fanout_queue_two";

        //routing模式
        public static final String ROUTING_EXCHANGE = "service_technical_routing";
        public static final String ROUTING_EXCHANGE_QUEUE_NAME_ONE = "routing_queue_one";
        public static final String ROUTING_EXCHANGE_QUEUE_NAME_TWO = "routing_queue_two";
        public static final String ROUTING_EXCHANGE_KEY_ONE = "routing_key_one";
        public static final String ROUTING_EXCHANGE_KEY_TWO = "routing_key_two";
        public static final String ROUTING_EXCHANGE_KEY_THR = "routing_key_thr";

        //Topics模式
        public static final String TOPICS_EXCHANGE = "service_technical_topics";
        public static final String TOPICS_EXCHANGE_QUEUE_NAME_ONE = "topics_queue_one";
        public static final String TOPICS_EXCHANGE_QUEUE_NAME_TWO = "topics_queue_two";
        public static final String TOPICS_EXCHANGE_KEY_ONE = "topics.*";
        public static final String TOPICS_EXCHANGE_KEY_TWO = "*.two";
        public static final String TOPICS_EXCHANGE_KEY_THR = "*.one";

    }
}
