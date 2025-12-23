-- Create sequences with custom start values and increments
CREATE SEQUENCE activities_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE categories_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE comments_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE communities_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE experiences_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE locations_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE media_content_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE plans_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE posts_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE schedules_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE tags_id_seq START WITH 1696658400 INCREMENT BY 7;
CREATE SEQUENCE users_id_seq START WITH 1696658400 INCREMENT BY 7;

-- Activities table
CREATE TABLE activities
(
    id             BIGINT PRIMARY KEY DEFAULT nextval('activities_id_seq'),
    name           VARCHAR(255)             NOT NULL,
    description    VARCHAR(1000),
    base_price     NUMERIC(38, 2)           NOT NULL,
    discount_price NUMERIC(38, 2),
    duration       INTEGER                  NOT NULL,
    location_id    BIGINT,
    provider_id    BIGINT,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255)
);

-- Activity categories junction table
CREATE TABLE activity_categories
(
    activities_id BIGINT NOT NULL,
    categories_id BIGINT NOT NULL,
    PRIMARY KEY (activities_id, categories_id)
);

-- Activity tags junction table
CREATE TABLE activity_tags
(
    activities_id BIGINT NOT NULL,
    tags_id       BIGINT NOT NULL,
    PRIMARY KEY (activities_id, tags_id)
);

-- Categories table
CREATE TABLE categories
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('categories_id_seq'),
    name       VARCHAR(255)             NOT NULL UNIQUE,
    hex_color  VARCHAR(7),
    media_id   BIGINT UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Client preferences table
CREATE TABLE client_preferences
(
    Client_id       BIGINT NOT NULL,
    preferredTagIds BIGINT
);

-- Comments table
CREATE TABLE comments
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('comments_id_seq'),
    content    TEXT,
    author_id  BIGINT                   NOT NULL,
    plan_id    BIGINT,
    post_id    BIGINT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Communities table
CREATE TABLE communities
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('communities_id_seq'),
    name        VARCHAR(255)             NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255)
);

-- Experiences table
CREATE TABLE experiences
(
    id          BIGINT PRIMARY KEY DEFAULT nextval('experiences_id_seq'),
    activity_id BIGINT                   NOT NULL,
    client_id   BIGINT                   NOT NULL,
    rating      INTEGER                  NOT NULL,
    review      VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE,
    created_by  VARCHAR(255),
    updated_by  VARCHAR(255)
);

-- Locations table
CREATE TABLE locations
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('locations_id_seq'),
    name       VARCHAR(255)             NOT NULL,
    address    VARCHAR(255)             NOT NULL,
    latitude   FLOAT                    NOT NULL,
    longitude  FLOAT                    NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Media content table
CREATE TABLE media_content
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('media_content_id_seq'),
    url           TEXT,
    type          VARCHAR(25) CHECK (type IN ('IMAGE', 'VIDEO', 'YOUTUBE', 'VIMEO')),
    activity_id   BIGINT,
    experience_id BIGINT,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255)
);

-- Payments table
CREATE TABLE payments
(
    id             VARCHAR(255) PRIMARY KEY,
    amount         NUMERIC(38, 2)           NOT NULL,
    currency       VARCHAR(255),
    status         VARCHAR(10)             NOT NULL CHECK (status IN ('PENDING', 'SUCCEEDED', 'FAILED', 'REFUNDED')),
    transaction_id VARCHAR(255) UNIQUE,
    plan_id        BIGINT UNIQUE,
    processed_at   TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Plans table
CREATE TABLE plans
(
    id               BIGINT PRIMARY KEY DEFAULT nextval('plans_id_seq'),
    title            VARCHAR(255),
    description      VARCHAR(1000),
    final_price      NUMERIC(38, 2)           NOT NULL,
    min_participants INTEGER                  NOT NULL,
    max_participants INTEGER,
    payment_type     SMALLINT CHECK (payment_type BETWEEN 0 AND 1),
    type             VARCHAR(10) CHECK (type IN ('PRIVATE', 'PUBLIC')),
    status           VARCHAR(10)             NOT NULL CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED')),
    client_id        BIGINT                   NOT NULL,
    schedule_id      BIGINT                   NOT NULL,
    start_time       TIMESTAMP                NOT NULL,
    end_time         TIMESTAMP                NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE,
    created_by       VARCHAR(255),
    updated_by       VARCHAR(255)
);

-- Plans comments junction table
CREATE TABLE plans_comments
(
    Plan_id     BIGINT NOT NULL,
    comments_id BIGINT NOT NULL UNIQUE,
    PRIMARY KEY (Plan_id, comments_id)
);

-- Plans users junction table
CREATE TABLE plans_users
(
    Plan_id         BIGINT NOT NULL,
    participants_id BIGINT NOT NULL,
    PRIMARY KEY (Plan_id, participants_id)
);

-- Posts table
CREATE TABLE posts
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('posts_id_seq'),
    title        VARCHAR(255)             NOT NULL,
    content      TEXT,
    author_id    BIGINT                   NOT NULL,
    community_id BIGINT                   NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITH TIME ZONE,
    created_by   VARCHAR(255),
    updated_by   VARCHAR(255)
);

-- Schedules table
CREATE TABLE schedules
(
    id             BIGINT PRIMARY KEY DEFAULT nextval('schedules_id_seq'),
    activity_id    BIGINT                   NOT NULL,
    start_date     DATE                     NOT NULL,
    end_date       DATE,
    start_time     TIME                     NOT NULL,
    end_time       TIME                     NOT NULL,
    recurrence     VARCHAR(255)             NOT NULL CHECK (recurrence IN ('SINGLE', 'DAILY', 'WEEKLY', 'CUSTOM_DAYS', 'HOLIDAYS')),
    recurring_days TEXT,
    excluded_dates TEXT,
    breaks         TEXT,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255)
);

-- Tags table
CREATE TABLE tags
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('tags_id_seq'),
    name       VARCHAR(50)              NOT NULL UNIQUE,
    type       VARCHAR(255)             NOT NULL CHECK (type IN ('ACTIVITY_TYPE', 'INTEREST', 'SKILL_LEVEL')),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(255),
    updated_by VARCHAR(255)
);

-- Users table
CREATE TABLE users
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq'),
    user_type     VARCHAR(31)              NOT NULL,
    user_name     VARCHAR(255) UNIQUE,
    email         VARCHAR(255) UNIQUE,
    password      VARCHAR(255),
    password_hash VARCHAR(255)             NOT NULL,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    business_name VARCHAR(255),
    logo          VARCHAR(255),
    tax_id        VARCHAR(255),
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE,
    created_by    VARCHAR(255),
    updated_by    VARCHAR(255)
);

-- Add foreign key constraints
ALTER TABLE activities
    ADD CONSTRAINT fk_activities_location FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE activities
    ADD CONSTRAINT fk_activities_provider FOREIGN KEY (provider_id) REFERENCES users (id);

ALTER TABLE activity_categories
    ADD CONSTRAINT fk_activity_categories_category FOREIGN KEY (categories_id) REFERENCES categories (id);

ALTER TABLE activity_categories
    ADD CONSTRAINT fk_activity_categories_activity FOREIGN KEY (activities_id) REFERENCES activities (id);

ALTER TABLE activity_tags
    ADD CONSTRAINT fk_activity_tags_tag FOREIGN KEY (tags_id) REFERENCES tags (id);

ALTER TABLE activity_tags
    ADD CONSTRAINT fk_activity_tags_activity FOREIGN KEY (activities_id) REFERENCES activities (id);

ALTER TABLE categories
    ADD CONSTRAINT fk_categories_media FOREIGN KEY (media_id) REFERENCES media_content (id);

ALTER TABLE client_preferences
    ADD CONSTRAINT fk_client_preferences_client FOREIGN KEY (Client_id) REFERENCES users (id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_author FOREIGN KEY (author_id) REFERENCES users (id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_plan FOREIGN KEY (plan_id) REFERENCES plans (id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comments_post FOREIGN KEY (post_id) REFERENCES posts (id);

ALTER TABLE experiences
    ADD CONSTRAINT fk_experiences_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

ALTER TABLE experiences
    ADD CONSTRAINT fk_experiences_client FOREIGN KEY (client_id) REFERENCES users (id);

ALTER TABLE media_content
    ADD CONSTRAINT fk_media_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

ALTER TABLE media_content
    ADD CONSTRAINT fk_media_experience FOREIGN KEY (experience_id) REFERENCES experiences (id);

ALTER TABLE payments
    ADD CONSTRAINT fk_payments_plan FOREIGN KEY (plan_id) REFERENCES plans (id);

ALTER TABLE plans
    ADD CONSTRAINT fk_plans_client FOREIGN KEY (client_id) REFERENCES users (id);

ALTER TABLE plans
    ADD CONSTRAINT fk_plans_schedule FOREIGN KEY (schedule_id) REFERENCES schedules (id);

ALTER TABLE plans_comments
    ADD CONSTRAINT fk_plans_comments_comment FOREIGN KEY (comments_id) REFERENCES comments (id);

ALTER TABLE plans_comments
    ADD CONSTRAINT fk_plans_comments_plan FOREIGN KEY (Plan_id) REFERENCES plans (id);

ALTER TABLE plans_users
    ADD CONSTRAINT fk_plans_users_user FOREIGN KEY (participants_id) REFERENCES users (id);

ALTER TABLE plans_users
    ADD CONSTRAINT fk_plans_users_plan FOREIGN KEY (Plan_id) REFERENCES plans (id);

ALTER TABLE posts
    ADD CONSTRAINT fk_posts_author FOREIGN KEY (author_id) REFERENCES users (id);

ALTER TABLE posts
    ADD CONSTRAINT fk_posts_community FOREIGN KEY (community_id) REFERENCES communities (id);

ALTER TABLE schedules
    ADD CONSTRAINT fk_schedules_activity FOREIGN KEY (activity_id) REFERENCES activities (id);

-- Set the sequences owned by columns
ALTER SEQUENCE activities_id_seq OWNED BY activities.id;
ALTER SEQUENCE categories_id_seq OWNED BY categories.id;
ALTER SEQUENCE comments_id_seq OWNED BY comments.id;
ALTER SEQUENCE communities_id_seq OWNED BY communities.id;
ALTER SEQUENCE experiences_id_seq OWNED BY experiences.id;
ALTER SEQUENCE locations_id_seq OWNED BY locations.id;
ALTER SEQUENCE media_content_id_seq OWNED BY media_content.id;
ALTER SEQUENCE plans_id_seq OWNED BY plans.id;
ALTER SEQUENCE posts_id_seq OWNED BY posts.id;
ALTER SEQUENCE schedules_id_seq OWNED BY schedules.id;
ALTER SEQUENCE tags_id_seq OWNED BY tags.id;
ALTER SEQUENCE users_id_seq OWNED BY users.id;