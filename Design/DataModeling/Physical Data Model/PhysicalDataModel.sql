-- Create database if not exists
CREATE
DATABASE IF NOT EXISTS aims;
USE
aims;

-- Media table - Base table for all media types
CREATE TABLE Media
(
    id       INTEGER PRIMARY KEY,
    type     VARCHAR(45) NOT NULL,
    category VARCHAR(45) NOT NULL,
    price    INT         NOT NULL,
    quantity INT         NOT NULL,
    title    VARCHAR(45) NOT NULL,
    value    INT         NOT NULL,
    imageUrl VARCHAR(45) NOT NULL,
    is_rush  BOOLEAN        DEFAULT FALSE,
    weight   DECIMAL(10, 2) DEFAULT 0.00
);

-- CD table - Inherits from Media
CREATE TABLE CD
(
    id           INT PRIMARY KEY,
    artist       VARCHAR(45) NOT NULL,
    recordLabel  VARCHAR(45) NOT NULL,
    musicType    VARCHAR(45) NOT NULL,
    releasedDate DATE,
    FOREIGN KEY (id) REFERENCES Media (id)
);

-- Book table - Inherits from Media
CREATE TABLE Book
(
    id           INT PRIMARY KEY,
    author       VARCHAR(45) NOT NULL,
    coverType    VARCHAR(45) NOT NULL,
    publisher    VARCHAR(45) NOT NULL,
    publishDate  DATETIME    NOT NULL,
    numOfPages   INT         NOT NULL,
    language     VARCHAR(45) NOT NULL,
    bookCategory VARCHAR(45) NOT NULL,
    FOREIGN KEY (id) REFERENCES Media (id)
);

-- DVD table - Inherits from Media
CREATE TABLE DVD
(
    id           INT PRIMARY KEY,
    discType     VARCHAR(45) NOT NULL,
    director     VARCHAR(45) NOT NULL,
    runtime      INT         NOT NULL,
    studio       VARCHAR(45) NOT NULL,
    subtitle     VARCHAR(45) NOT NULL,
    releasedDate DATETIME,
    filmType     VARCHAR(45) NOT NULL,
    FOREIGN KEY (id) REFERENCES Media (id)
);

-- Order table - Contains delivery info and order details
CREATE TABLE `Order`
(
    id           VARCHAR(45) PRIMARY KEY,
    -- DeliveryInfo fields
    name         VARCHAR(45) NOT NULL,
    email        VARCHAR(45) NOT NULL,
    address      VARCHAR(45) NOT NULL,
    phone        VARCHAR(45) NOT NULL,
    province     VARCHAR(45) NOT NULL,
    -- Order specific fields
    shipping_fee INT         NOT NULL,
    totalAmount DOUBLE NOT NULL,
    orderStatus  VARCHAR(45) DEFAULT 'PENDING',
    paymentType  VARCHAR(45) NOT NULL,
    is_rush      BOOLEAN     DEFAULT FALSE
);

-- OrderMedia table - Junction table for Order and Media
CREATE TABLE OrderMedia
(
    mediaID  INT NOT NULL,
    orderID  VARCHAR(45) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (mediaID, orderID),
    FOREIGN KEY (mediaID) REFERENCES Media (id),
    FOREIGN KEY (orderID) REFERENCES `Order` (id)
);

-- Transaction table - Records order transactions
CREATE TABLE PaymentTransaction
(
    id             INTEGER PRIMARY KEY,
    orderID        VARCHAR(45) NOT NULL,
    content        VARCHAR(255) NOT NULL,
    createAt       DATETIME     NOT NULL,
    status         VARCHAR(45)  NOT NULL,
    amount         INT          NOT NULL,
    paymentType    VARCHAR(45)  NOT NULL,
    transactionNum VARCHAR(45)  NOT NULL,
    FOREIGN KEY (orderID) REFERENCES `Order` (id)
);

-- Indexes for performance optimization
CREATE INDEX idx_ordermedia_orderid ON OrderMedia (orderID);
CREATE INDEX idx_transaction_orderid ON PaymentTransaction (orderID);