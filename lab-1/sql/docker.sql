DROP TABLE IF EXISTS docker;

CREATE TABLE docker (
   id         INT          NOT NULL AUTO_INCREMENT,
   event_time DATETIME     NOT NULL,
   severity   VARCHAR(40)  NOT NULL,
   message    VARCHAR(200) NOT NULL,

   PRIMARY KEY (id)
) ;

INSERT INTO docker (id,event_time,severity,message)
VALUES (1,NOW(),'INFO','Initial configuration');

