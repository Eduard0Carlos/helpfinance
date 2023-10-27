CREATE TABLE users (
    id char(36) NOT NULL,
    name varchar(100) NOT NULL,
    cpf varchar(11) NULL,
    image blob NULL,
    birthdate date NOT NULL,
    childrenNumber int NULL,
    email varchar(100) NOT NULL,
    password varchar(20) NOT NULL,
    balance integer NOT NULL,
    monthlySpendingsLimit integer NULL,
    investmentProfileLevel int NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_users PRIMARY KEY (id)
);

CREATE TABLE address (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    cep varchar2(8) NOT NULL,
    houseNumber varchar2(20) NOT NULL,
    street varchar2(100) NOT NULL,
    city varchar2(100) NOT NULL,
    state varchar2(50) NOT NULL,
    country varchar2(50) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_address PRIMARY KEY (id)
);

CREATE TABLE movimentations (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    title varchar2(50) NOT NULL,
    amount int NOT NULL,
    category varchar(2000) NOT NULL,
    movType varchar(2000) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_movimentations PRIMARY KEY (id)
);

CREATE TABLE recurrents (
    id char(36) NOT NULL,
    movimentationId char(36) NOT NULL,
    startDate date NOT NULL,
    endDate date NOT NULL,
    daysOfWeek varchar(2000) NULL,
    isMonthly number(1) NOT NULL,
    isAnnual number(1) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_recurrents PRIMARY KEY (id)
);

CREATE TABLE payment_cards (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    cardNumber varchar2(16) NOT NULL,
    nickname varchar2(40) NOT NULL,
    expirationDate date NOT NULL,
    cvv varchar2(3) NOT NULL,
    paymentNetwork varchar(2000) NOT NULL,
    paymentType varchar(2000) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_payment_cards PRIMARY KEY (id)
);

CREATE TABLE banks (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    name varchar2(60) NOT NULL,
    bankAccountNumber number(10) NOT NULL,
    bankAgencyNumber number(10) NOT NULL,
    integrationToken varchar2(200) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_banks PRIMARY KEY (id)
);

CREATE TABLE investments (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    stockId varchar2(10) NOT NULL,
    amount number(19) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_investments PRIMARY KEY (id)
);

CREATE TABLE jobs (
    id char(36) NOT NULL,
    userId char(36) NOT NULL,
    companyName varchar2(50) NOT NULL,
    title varchar2(60) NOT NULL,
    netSallary number(19) NOT NULL,
    active smallint NOT NULL,
    createdAt date NOT NULL,
    updatedAt date NULL,
    CONSTRAINT PK_jobs PRIMARY KEY (id)
);

ALTER TABLE
    address
ADD
    CONSTRAINT FK_address_user_id FOREIGN KEY(userId) REFERENCES users (id);

ALTER TABLE
    movimentations
ADD
    CONSTRAINT FK_movimentations_user_id FOREIGN KEY(userId) REFERENCES users (id);

ALTER TABLE
    recurrents
ADD
    CONSTRAINT FK_recurrents_movimentation_id FOREIGN KEY(movimentationId) REFERENCES movimentations (id);

ALTER TABLE
    payment_cards
ADD
    CONSTRAINT FK_payment_cards_user_id FOREIGN KEY(userId) REFERENCES users (id);

ALTER TABLE
    banks
ADD
    CONSTRAINT FK_banks_user_id FOREIGN KEY(userId) REFERENCES users (id);

ALTER TABLE
    investments
ADD
    CONSTRAINT FK_investments_user_id FOREIGN KEY(userId) REFERENCES users (id);

ALTER TABLE
    jobs
ADD
    CONSTRAINT FK_jobs_user_id FOREIGN KEY(userId) REFERENCES users (id);