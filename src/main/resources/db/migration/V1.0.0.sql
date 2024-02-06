CREATE TABLE product_master
(
    id           UUID        NOT NULL,
    name         VARCHAR(50) NOT NULL,
    price        DOUBLE PRECISION,
    category     VARCHAR(50) NOT NULL,
    enable       BOOLEAN     NOT NULL,
    date_created date        NOT NULL,
    CONSTRAINT pk_product_master PRIMARY KEY (id)
);

ALTER TABLE product_master
    ADD CONSTRAINT uc_product_master_name UNIQUE (name);

CREATE TABLE product_detail
(
    id                UUID NOT NULL,
    description       VARCHAR(200),
    id_product_master UUID,
    CONSTRAINT pk_product_detail PRIMARY KEY (id)
);

ALTER TABLE product_detail
    ADD CONSTRAINT uc_product_detail_id_product_master UNIQUE (id_product_master);

ALTER TABLE product_detail
    ADD CONSTRAINT FK_PRODUCT_DETAIL_ON_ID_PRODUCT_MASTER FOREIGN KEY (id_product_master) REFERENCES product_master (id);