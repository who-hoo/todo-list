DROP TABLE IF EXISTS MEMBER;

CREATE TABLE MEMBER
(
    ID       LONG PRIMARY KEY AUTO_INCREMENT,
    USER_ID  VARCHAR(500),
    PASSWORD VARCHAR(500),
    NAME     VARCHAR(500)
);

DROP TABLE IF EXISTS TODO_CARD;

CREATE TABLE TODO_CARD
(
    ID             LONG PRIMARY KEY AUTO_INCREMENT,
    STATUS         LONG         NOT NULL,
    TITLE          VARCHAR(500) NOT NULL,
    CONTENT        VARCHAR(500),
    USER_ID        VARCHAR(500) NOT NULL,
    APPLIANCE_INFO LONG         NOT NULL,
    CREATED_AT     DATETIME     NOT NULL,
    MODIFIED_AT    DATETIME,
    DELETED_YN     BOOLEAN      NOT NULL,
    INDEX          LONG         NOT NULL
);

DROP TABLE IF EXISTS ACTIVITY_LOG;

CREATE TABLE ACTIVITY_LOG
(
    ID            LONG PRIMARY KEY AUTO_INCREMENT,
    USER_ID       VARCHAR(500) NOT NULL,
    ACTIVITY_TYPE LONG         NOT NULL COMMENT 'ADD, REMOVE, UPDATE, MOVE',
    FROM_STATUS   LONG COMMENT '변경 전 상태',
    TO_STATUS     LONG COMMENT '변경 후 상태',
    CREATED_AT    DATETIME     NOT NULL,
    READ_YN       BOOLEAN      NOT NULL COMMENT '읽기 여부'
);
