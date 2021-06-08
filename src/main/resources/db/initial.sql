CREATE TABLE user
(
    id         INT          NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    user_role  VARCHAR(255),
    CONSTRAINT PK_user PRIMARY KEY (id)
);

CREATE TABLE transport_type
(
    id          INT          NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT PK_transport_type PRIMARY KEY (id),
    CONSTRAINT UC_transport_type UNIQUE (description)
);

CREATE TABLE route
(
    id               INT          NOT NULL AUTO_INCREMENT,
    number           INT          NOT NULL,
    description      VARCHAR(255) NOT NULL,
    start_weekday    TIME         NOT NULL,
    end_weekday      TIME         NOT NULL,
    interval_weekday TIME         NOT NULL,
    start_dayoff     TIME         NOT NULL,
    end_dayoff       TIME         NOT NULL,
    interval_dayoff  TIME         NOT NULL,
    CONSTRAINT PK_route PRIMARY KEY (id)
);

CREATE TABLE stop
(
    id   INT          NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT PK_stop PRIMARY KEY (id)
);

CREATE TABLE route_line
(
    id            INT  NOT NULL AUTO_INCREMENT,
    route_id      INT  NOT NULL,
    stop_id       INT  NOT NULL,
    stop_order    INT  NOT NULL,
    time_prev     TIME NOT NULL,
    distance_prev INT  NOT NULL,
    CONSTRAINT PK_route_line PRIMARY KEY (id),
    CONSTRAINT FK_route_line_route FOREIGN KEY (route_id) REFERENCES route (id),
    CONSTRAINT FK_route_line_stop FOREIGN KEY (stop_id) REFERENCES stop (id)
);

CREATE TABLE transport
(
    id            INT           NOT NULL AUTO_INCREMENT,
    type_id       INT           NOT NULL,
    model         VARCHAR(255)  NOT NULL,
    seat_num      INT           NOT NULL,
    drive_type    VARCHAR(255)  NOT NULL,
    len           DECIMAL(5, 2) NOT NULL,
    width         DECIMAL(5, 2) NOT NULL,
    door_num      INT           NOT NULL,
    battery_power INT,
    car_num       INT,
    route_id      INT,
    CONSTRAINT PK_transport PRIMARY KEY (id),
    CONSTRAINT FK_PK_transport_transport_type FOREIGN KEY (type_id) REFERENCES transport_type (id),
    CONSTRAINT FK_PK_transport_route FOREIGN KEY (route_id) REFERENCES route (id)
)