CREATE SEQUENCE ORDER_SEQ START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE ORDER_ITEM_SEQ START WITH 1000 INCREMENT BY 1;
-- ORDER
CREATE TABLE "order"
(
    ID                  INTEGER,
    ORDER_STATUS_ID     SMALLINT,
    CUSTOMER_FIRST_NAME VARCHAR(64),
    CUSTOMER_LAST_NAME  VARCHAR(64),
    CUSTOMER_BIRTHDAY   DATE,
    CUSTOMER_PHONE      VARCHAR(24),
    CUSTOMER_COMMENT    VARCHAR(128)
);
ALTER TABLE "order"
    ADD CONSTRAINT ORDER_ID_PK PRIMARY KEY (ID);
CREATE INDEX ORDER_PK_IDX ON "order" (ID);
-- ORDER ITEM
CREATE TABLE ORDER_ITEM
(
    ID        INTEGER,
    ORDER_ID  INTEGER,
    ITEM_NAME VARCHAR(64)
);
ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT ORDER_ITEM_ID_PK PRIMARY KEY (ID);
ALTER TABLE ORDER_ITEM
    ADD CONSTRAINT OI_O_FK FOREIGN KEY (ORDER_ID)
        REFERENCES "order" (ID);
CREATE INDEX ORDER_ITEM_PK_IDX ON ORDER_ITEM (ID);
CREATE INDEX ORDER_ITEM_FK_IDX ON ORDER_ITEM (ORDER_ID);

DO $$
    DECLARE
        v_order_id      integer;
        v_order_item_id integer;
    BEGIN
        SELECT NEXTVAL('order_seq') INTO v_order_id;
        INSERT INTO "order"(ID, ORDER_STATUS_ID, CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME, CUSTOMER_BIRTHDAY,
                            CUSTOMER_PHONE, CUSTOMER_COMMENT)
        VALUES (v_order_id, 1, 'Ivan', 'Ivanov', '15-10-1995', '+7(952)-634-55-23', 'Pls. call before delivery');

        SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
        INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
        VALUES (v_order_item_id, v_order_id, 'Order Item #1');
        SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
        INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
        VALUES (v_order_item_id, v_order_id, 'Order Item #2');
        SELECT NEXTVAL('order_item_seq') INTO v_order_item_id;
        INSERT INTO ORDER_ITEM (ID, ORDER_ID, ITEM_NAME)
        VALUES (v_order_item_id, v_order_id, 'Order Item #3');
    END
$$;

CREATE SEQUENCE SESSIONS_SEQ START WITH 1000 INCREMENT BY 1;

CREATE TABLE SESSIONS
(
    ID              INTEGER,
    SESSION_ID      VARCHAR(24),
    START_TIME      DATE,
    TIMEOUT_MINUTES INTEGER
);
ALTER TABLE SESSIONS
    ADD CONSTRAINT SESSIONS_ID_PK PRIMARY KEY (ID);
CREATE INDEX SESSIONS_PK_IDX ON SESSIONS (ID);

DO $$
    DECLARE
        v_session_id      integer;
    BEGIN
        SELECT NEXTVAL('sessions_seq') INTO v_session_id;
        INSERT INTO SESSIONS (ID, SESSION_ID, START_TIME, TIMEOUT_MINUTES)
        VALUES (v_session_id, '1234-AAFF-BB55-DD22', NOW(), 300);
    END
$$;