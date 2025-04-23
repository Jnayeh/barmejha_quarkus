create table activities
(
    id             bigint                      not null,
    created_at     timestamp(6) with time zone not null,
    created_by     varchar(255),
    updated_at     timestamp(6) with time zone,
    updated_by     varchar(255),
    base_price     numeric(38, 2)              not null,
    description    varchar(1000),
    discount_price numeric(38, 2),
    duration       integer                     not null,
    name           varchar(255)                not null,
    location_id    bigint,
    provider_id    bigint,
    primary key (id)
);

create table activity_categories
(
    activities_id bigint not null,
    categories_id bigint not null,
    primary key (activities_id, categories_id)
);

create table activity_tags
(
    activities_id bigint not null,
    tags_id       bigint not null,
    primary key (activities_id, tags_id)
);

create table categories
(
    id          bigint                      not null,
    created_at  timestamp(6) with time zone not null,
    created_by  varchar(255),
    updated_at  timestamp(6) with time zone,
    updated_by  varchar(255),
    hex_color   varchar(7),
    name        varchar(255)                not null,
    category_id bigint                      not null,
    primary key (id)
);

create table client_preferences
(
    Client_id       bigint not null,
    preferredTagIds bigint
);

create table comments
(
    id         bigint                      not null,
    created_at timestamp(6) with time zone not null,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    content    TEXT,
    author_id  bigint                      not null,
    plan_id    bigint,
    post_id    bigint,
    primary key (id)
);

create table communities
(
    id          bigint                      not null,
    created_at  timestamp(6) with time zone not null,
    created_by  varchar(255),
    updated_at  timestamp(6) with time zone,
    updated_by  varchar(255),
    description varchar(255),
    name        varchar(255)                not null,
    primary key (id)
);

create table experiences
(
    id          bigint                      not null,
    created_at  timestamp(6) with time zone not null,
    created_by  varchar(255),
    updated_at  timestamp(6) with time zone,
    updated_by  varchar(255),
    rating      integer                     not null,
    review      varchar(255),
    activity_id bigint                      not null,
    client_id   bigint                      not null,
    primary key (id)
);

create table locations
(
    id         bigint                      not null,
    created_at timestamp(6) with time zone not null,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    address    varchar(255)                not null,
    latitude   float(53)                   not null,
    longitude  float(53)                   not null,
    name       varchar(255)                not null,
    primary key (id)
);

create table media_content
(
    id            bigint                      not null,
    created_at    timestamp(6) with time zone not null,
    created_by    varchar(255),
    updated_at    timestamp(6) with time zone,
    updated_by    varchar(255),
    type          varchar(15) check (type in ('IMAGE', 'VIDEO', 'YOUTUBE', 'VIMEO')),
    url           TEXT,
    activity_id   bigint,
    experience_id bigint                      not null,
    primary key (id)
);

create table payments
(
    id             varchar(255)                not null,
    amount         numeric(38, 2)              not null,
    currency       varchar(255),
    processed_at   timestamp(6) with time zone not null,
    status         varchar(15)                not null check (status in ('PENDING', 'SUCCEEDED', 'FAILED', 'REFUNDED')),
    transaction_id varchar(255),
    plan_id        bigint,
    primary key (id)
);

create table plans
(
    id               bigint                      not null,
    created_at       timestamp(6) with time zone not null,
    created_by       varchar(255),
    updated_at       timestamp(6) with time zone,
    updated_by       varchar(255),
    description      varchar(1000),
    end_time         timestamp(6)                not null,
    final_price      numeric(38, 2)              not null,
    max_participants integer,
    min_participants integer                     not null,
    payment_type     smallint check (payment_type between 0 and 1),
    start_time       timestamp(6)                not null,
    status           varchar(15)                not null check (status in ('PENDING', 'CONFIRMED', 'CANCELLED', 'COMPLETED')),
    title            varchar(255),
    type             varchar(7) check (type in ('PRIVATE', 'PUBLIC')),
    client_id        bigint                      not null,
    schedule_id      bigint                      not null,
    primary key (id)
);

create table plans_comments
(
    Plan_id     bigint not null,
    comments_id bigint not null,
    primary key (Plan_id, comments_id)
);

create table plans_users
(
    Plan_id         bigint not null,
    participants_id bigint not null,
    primary key (Plan_id, participants_id)
);

create table posts
(
    id           bigint                      not null,
    created_at   timestamp(6) with time zone not null,
    created_by   varchar(255),
    updated_at   timestamp(6) with time zone,
    updated_by   varchar(255),
    content      TEXT,
    title        varchar(255)                not null,
    author_id    bigint                      not null,
    community_id bigint                      not null,
    primary key (id)
);

create table schedules
(
    id             bigint                      not null,
    created_at     timestamp(6) with time zone not null,
    created_by     varchar(255),
    updated_at     timestamp(6) with time zone,
    updated_by     varchar(255),
    breaks         TEXT,
    end_date       date,
    end_time       time(6)                     not null,
    excluded_dates TEXT,
    recurrence     varchar(15)                not null check (recurrence in ('SINGLE', 'DAILY', 'WEEKLY', 'CUSTOM_DAYS', 'HOLIDAYS')),
    recurring_days TEXT,
    start_date     date                        not null,
    start_time     time(6)                     not null,
    activity_id    bigint                      not null,
    primary key (id)
);

create table tags
(
    id         bigint                      not null,
    created_at timestamp(6) with time zone not null,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    name       varchar(50)                 not null,
    type       varchar(15)                not null check (type in ('ACTIVITY_TYPE', 'INTEREST', 'SKILL_LEVEL')),
    primary key (id)
);

create table users
(
    user_type     varchar(31)                 not null,
    id            bigint                      not null,
    created_at    timestamp(6) with time zone not null,
    created_by    varchar(255),
    updated_at    timestamp(6) with time zone,
    updated_by    varchar(255),
    email         varchar(255),
    first_name    varchar(255),
    last_name     varchar(255),
    password_hash varchar(255)                not null,
    user_name     varchar(255),
    business_name varchar(255),
    logo          varchar(255),
    tax_id        varchar(255),
    primary key (id)
);

alter table if exists categories
    drop constraint if exists UKt8o6pivur7nn124jehx7cygw5;

alter table if exists categories
    add constraint UKt8o6pivur7nn124jehx7cygw5 unique (name);

alter table if exists categories
    drop constraint if exists UKivqt9e06jfskg7wb1jfpf6836;

alter table if exists categories
    add constraint UKivqt9e06jfskg7wb1jfpf6836 unique (category_id);
alter table if exists communities
    drop constraint if exists UK3vr2q12p5v4unsi7025edy28c;
alter table if exists communities
    add constraint UK3vr2q12p5v4unsi7025edy28c unique (name);
alter table if exists payments
    drop constraint if exists UKlryndveuwa4k5qthti0pkmtlx;
alter table if exists payments
    add constraint UKlryndveuwa4k5qthti0pkmtlx unique (transaction_id);
alter table if exists payments
    drop constraint if exists UKonidj3392lq6bv74kclrevjrx;
alter table if exists payments
    add constraint UKonidj3392lq6bv74kclrevjrx unique (plan_id);
alter table if exists plans_comments
    drop constraint if exists UKemco5uys9gjdvwyjd6l1umg0q;
alter table if exists plans_comments
    add constraint UKemco5uys9gjdvwyjd6l1umg0q unique (comments_id);
alter table if exists tags
    drop constraint if exists UKt48xdq560gs3gap9g7jg36kgc;
alter table if exists tags
    add constraint UKt48xdq560gs3gap9g7jg36kgc unique (name);
alter table if exists users
    drop constraint if exists UK6dotkott2kjsp8vw4d0m25fb7;
alter table if exists users
    add constraint UK6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table if exists users
    drop constraint if exists UKk8d0f2n7n88w1a16yhua64onx;
alter table if exists users
    add constraint UKk8d0f2n7n88w1a16yhua64onx unique (user_name);
alter table if exists activities
    add constraint FK3i7f1od1x93s60ul9j48qpdpe
        foreign key (location_id)
            references locations;
alter table if exists activities
    add constraint FK4dvp1jo35locfryxlugncpl11
        foreign key (provider_id)
            references users;
alter table if exists activity_categories
    add constraint FKh92223lxomwumgb2pm51979m1
        foreign key (categories_id)
            references categories;
alter table if exists activity_categories
    add constraint FK4fymmnudu4nmba3texnnxp5lr
        foreign key (activities_id)
            references activities;
alter table if exists activity_tags
    add constraint FKbeuo3k8n8i34kv0qwj6un5vlc
        foreign key (tags_id)
            references tags;
alter table if exists activity_tags
    add constraint FK6l27a8g205f1ikfrchp7kp4df
        foreign key (activities_id)
            references activities;
alter table if exists categories
    add constraint FKpvbv0s0kvln8xvjyf6jtfk10y
        foreign key (category_id)
            references media_content;
alter table if exists client_preferences
    add constraint FKqk1kbvuaa332lth5tx88tf14x
        foreign key (Client_id)
            references users;
alter table if exists comments
    add constraint FKn2na60ukhs76ibtpt9burkm27
        foreign key (author_id)
            references users;
alter table if exists comments
    add constraint FKf0340npvkkasvyk0q2nppxxcq
        foreign key (plan_id)
            references plans;
alter table if exists comments
    add constraint FKh4c7lvsc298whoyd4w9ta25cr
        foreign key (post_id)
            references posts;
alter table if exists experiences
    add constraint FK1bxwtkv9ci1rx3b1510qrmutw
        foreign key (activity_id)
            references activities;
alter table if exists experiences
    add constraint FKg8e6uccu4oaif9ooybnkck0h7
        foreign key (client_id)
            references users;
alter table if exists media_content
    add constraint FKjagpvoh5q3uflo05bhgq5v1n4
        foreign key (activity_id)
            references activities;
alter table if exists media_content
    add constraint FKpeevcy0ly4tdy2cefsm5roe8g
        foreign key (experience_id)
            references experiences;
alter table if exists payments
    add constraint FK70f50cqfiyp9w8qi5fgikvsxd
        foreign key (plan_id)
            references plans;
alter table if exists plans
    add constraint FK1oyp76gx3ua5c6025rc3xbgsu
        foreign key (client_id)
            references users;
alter table if exists plans
    add constraint FKekr7032gliq2rgmmc5s6fcpfg
        foreign key (schedule_id)
            references schedules;
alter table if exists plans_comments
    add constraint FKr3ngnoi2vkd3i61ov55rpmlrb
        foreign key (comments_id)
            references comments;
alter table if exists plans_comments
    add constraint FKseug77tww9p8tffhvwcl4mvtf
        foreign key (Plan_id)
            references plans;
alter table if exists plans_users
    add constraint FK10inbii6dpuoxwaotute2mffk
        foreign key (participants_id)
            references users;
alter table if exists plans_users
    add constraint FKtne8bxemaw5bqeevu56ymxsly
        foreign key (Plan_id)
            references plans;
alter table if exists posts
    add constraint FK6xvn0811tkyo3nfjk2xvqx6ns
        foreign key (author_id)
            references users;
alter table if exists posts
    add constraint FK7rk45ficmsfhe8n1dojvqt6ui
        foreign key (community_id)
            references communities;
alter table if exists schedules
    add constraint FK3tyc3usongts2dlum3e6c5in4
        foreign key (activity_id)
            references activities;