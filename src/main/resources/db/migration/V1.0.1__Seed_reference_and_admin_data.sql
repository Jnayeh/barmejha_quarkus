-- 1. Seed tables with NO foreign key dependencies first
-- Seed users (providers and clients) - no dependencies
INSERT INTO users (id, user_type, user_name, email, password, password_hash, first_name, last_name, business_name, logo, tax_id, created_at, updated_at, created_by, updated_by)
VALUES 
    (1696658400, 'PROVIDER', 'outdoor_adventures', 'adventures@outdoor.com', NULL, 'hashed_password_1', 'John', 'Adventure', 'Outdoor Adventures LLC', 'https://example.com/logo1.png', 'TAX123456', '2023-10-01 10:00:00+00', NULL, 'system', NULL),
    (1696658407, 'CLIENT', 'alice_wanderer', 'alice@email.com', NULL, 'hashed_password_2', 'Alice', 'Smith', NULL, NULL, NULL, '2023-10-01 10:30:00+00', NULL, 'system', NULL),
    (1696658414, 'PROVIDER', 'culinary_masters', 'info@culinarymasters.com', NULL, 'hashed_password_3', 'Maria', 'Chef', 'Culinary Masters Inc.', 'https://example.com/logo2.png', 'TAX789012', '2023-10-01 11:00:00+00', NULL, 'system', NULL),
    (1696658421, 'CLIENT', 'bob_explorer', 'bob@email.com', NULL, 'hashed_password_4', 'Bob', 'Johnson', NULL, NULL, NULL, '2023-10-01 11:30:00+00', NULL, 'system', NULL),
    (1696658428, 'CLIENT', 'charlie_traveler', 'charlie@email.com', NULL, 'hashed_password_5', 'Charlie', 'Brown', NULL, NULL, NULL, '2023-10-01 12:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- Seed locations - no dependencies
INSERT INTO locations (id, name, address, latitude, longitude, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Central Park', '59th to 110th Street, New York, NY', 40.785091, -73.968285, '2023-10-01 09:00:00+00', NULL, 'system', NULL),
    (1696658407, 'Downtown Studio', '123 Main St, New York, NY', 40.748817, -73.985428, '2023-10-01 09:30:00+00', NULL, 'system', NULL),
    (1696658414, 'Mountain Trailhead', '456 Mountain Rd, Catskill, NY', 42.221650, -74.230390, '2023-10-01 10:00:00+00', NULL, 'system', NULL),
    (1696658421, 'Riverside Kitchen', '789 River Ave, Brooklyn, NY', 40.702500, -73.989000, '2023-10-01 10:30:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- Seed tags - no dependencies
INSERT INTO tags (id, name, type, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'HIKING', 'ACTIVITY_TYPE', '2023-10-01 08:00:00+00', NULL, 'system', NULL),
    (1696658407, 'COOKING', 'ACTIVITY_TYPE', '2023-10-01 08:07:00+00', NULL, 'system', NULL),
    (1696658414, 'OUTDOORS', 'INTEREST', '2023-10-01 08:14:00+00', NULL, 'system', NULL),
    (1696658421, 'FOOD', 'INTEREST', '2023-10-01 08:21:00+00', NULL, 'system', NULL),
    (1696658428, 'BEGINNER', 'SKILL_LEVEL', '2023-10-01 08:28:00+00', NULL, 'system', NULL),
    (1696658435, 'INTERMEDIATE', 'SKILL_LEVEL', '2023-10-01 08:35:00+00', NULL, 'system', NULL),
    (1696658442, 'YOGA', 'ACTIVITY_TYPE', '2023-10-01 08:42:00+00', NULL, 'system', NULL),
    (1696658449, 'WELLNESS', 'INTEREST', '2023-10-01 08:49:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 2. Seed tables that depend ONLY on already seeded tables
-- Seed media_content for categories (no activity_id or experience_id yet)
INSERT INTO media_content (id, url, type, activity_id, experience_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'https://example.com/hiking-act1.jpg', 'IMAGE', NULL, NULL, '2023-10-01 08:45:00+00', NULL, 'system', NULL),
    (1696658407, 'https://example.com/cooking-act1.jpg', 'IMAGE', NULL, NULL, '2023-10-01 08:52:00+00', NULL, 'system', NULL),
    (1696658414, 'https://example.com/yoga-act1.jpg', 'IMAGE', NULL, NULL, '2023-10-01 08:59:00+00', NULL, 'system', NULL),
    (1696658421, 'https://example.com/category-hiking.jpg', 'IMAGE', NULL, NULL, '2023-10-01 09:05:00+00', NULL, 'system', NULL),
    (1696658428, 'https://example.com/category-cooking.jpg', 'IMAGE', NULL, NULL, '2023-10-01 09:12:00+00', NULL, 'system', NULL),
    (1696658435, 'https://example.com/category-wellness.jpg', 'IMAGE', NULL, NULL, '2023-10-01 09:19:00+00', NULL, 'system', NULL),
    (1696658442, 'https://example.com/category-fitness.jpg', 'IMAGE', NULL, NULL, '2023-10-01 09:26:00+00', NULL, 'system', NULL),
    (1696658449, 'https://example.com/category-nature.jpg', 'IMAGE', NULL, NULL, '2023-10-01 09:33:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- Seed categories (depends on media_content)
INSERT INTO categories (id, name, hex_color, media_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Adventure', '#FF6B35', 1696658421, '2023-10-01 09:00:00+00', NULL, 'system', NULL),
    (1696658407, 'Culinary', '#4ECDC4', 1696658428, '2023-10-01 09:07:00+00', NULL, 'system', NULL),
    (1696658414, 'Wellness', '#45B7D1', 1696658435, '2023-10-01 09:14:00+00', NULL, 'system', NULL),
    (1696658421, 'Fitness', '#96CEB4', 1696658442, '2023-10-01 09:21:00+00', NULL, 'system', NULL),
    (1696658428, 'Nature', '#FEBE10', 1696658449, '2023-10-01 09:28:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- Seed activities (depends on locations and users)
INSERT INTO activities (id, name, description, base_price, discount_price, duration, location_id, provider_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Morning Hike in the Catskills', 'Enjoy a guided morning hike through beautiful mountain trails with scenic views.', 50.00, 45.00, 180, 1696658414, 1696658400, '2023-10-01 10:00:00+00', NULL, 'system', NULL),
    (1696658407, 'Italian Cooking Class', 'Learn to make authentic pasta and tiramisu from scratch with a professional chef.', 75.00, 65.00, 120, 1696658421, 1696658414, '2023-10-01 10:07:00+00', NULL, 'system', NULL),
    (1696658414, 'Central Park Yoga Session', 'Morning yoga session in the heart of Central Park, suitable for all levels.', 30.00, NULL, 60, 1696658400, 1696658400, '2023-10-01 10:14:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 4. Seed more media_content to link to activities
INSERT INTO media_content (id, url, type, activity_id, experience_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658456, 'https://example.com/hike-activity-main.jpg', 'IMAGE', 1696658400, NULL, '2023-10-01 10:20:00+00', NULL, 'system', NULL),
    (1696658463, 'https://example.com/cooking-activity-main.jpg', 'IMAGE', 1696658407, NULL, '2023-10-01 10:27:00+00', NULL, 'system', NULL),
    (1696658470, 'https://example.com/yoga-activity-main.jpg', 'IMAGE', 1696658414, NULL, '2023-10-01 10:34:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 5. Seed junction tables that depend on activities, categories, tags
-- Seed activity_categories - each activity relates to at least 2 categories
INSERT INTO activity_categories (activities_id, categories_id)
VALUES
    -- Hiking activity relates to Adventure, Nature, and Fitness
    (1696658400, 1696658400), -- Hiking -> Adventure
    (1696658400, 1696658428), -- Hiking -> Nature
    (1696658400, 1696658421), -- Hiking -> Fitness
    -- Cooking activity relates to Culinary and Food
    (1696658407, 1696658407), -- Cooking -> Culinary
    (1696658407, 1696658421), -- Cooking -> Fitness (cooking can be physical)
    -- Yoga activity relates to Wellness and Fitness
    (1696658414, 1696658414), -- Yoga -> Wellness
    (1696658414, 1696658421)  -- Yoga -> Fitness
ON CONFLICT (activities_id, categories_id) DO NOTHING;

-- Seed activity_tags
INSERT INTO activity_tags (activities_id, tags_id)
VALUES
    (1696658400, 1696658400), -- Hiking activity -> HIKING tag
    (1696658400, 1696658414), -- Hiking activity -> OUTDOORS tag
    (1696658400, 1696658428), -- Hiking activity -> BEGINNER tag
    (1696658407, 1696658407), -- Cooking activity -> COOKING tag
    (1696658407, 1696658421), -- Cooking activity -> FOOD tag
    (1696658407, 1696658428), -- Cooking activity -> BEGINNER tag
    (1696658414, 1696658442), -- Yoga activity -> YOGA tag
    (1696658414, 1696658449), -- Yoga activity -> WELLNESS tag
    (1696658414, 1696658428)  -- Yoga activity -> BEGINNER tag
ON CONFLICT (activities_id, tags_id) DO NOTHING;

-- 6. Seed schedules (depends on activities)
INSERT INTO schedules (id, activity_id, start_date, end_date, start_time, end_time, recurrence, recurring_days, excluded_dates, breaks, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 1696658400, '2023-10-15', '2023-12-15', '08:00:00', '11:00:00', 'WEEKLY', '["SATURDAY", "SUNDAY"]', '["2023-11-23"]', '[]', '2023-10-01 11:00:00+00', NULL, 'system', NULL),
    (1696658407, 1696658407, '2023-10-20', '2023-12-20', '18:00:00', '20:00:00', 'WEEKLY', '["FRIDAY"]', '["2023-11-24"]', '[]', '2023-10-01 11:07:00+00', NULL, 'system', NULL),
    (1696658414, 1696658414, '2023-10-10', NULL, '07:00:00', '08:00:00', 'DAILY', NULL, '[]', '[]', '2023-10-01 11:14:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 7. Seed communities (no dependencies)
INSERT INTO communities (id, name, description, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Outdoor Enthusiasts', 'A community for hiking, camping, and outdoor adventures', '2023-10-01 12:00:00+00', NULL, 'system', NULL),
    (1696658407, 'Food Lovers NYC', 'Share recipes, restaurants, and cooking tips in New York', '2023-10-01 12:07:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 8. Seed plans (depends on users and schedules)
INSERT INTO plans (id, title, description, final_price, min_participants, max_participants, payment_type, type, status, client_id, schedule_id, start_time, end_time, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Weekend Hiking Trip', 'Private hiking trip for friends', 180.00, 4, 8, 1, 'PRIVATE', 'CONFIRMED', 1696658407, 1696658400, '2023-10-21 08:00:00', '2023-10-21 11:00:00', '2023-10-05 14:00:00+00', NULL, 'alice_wanderer', NULL),
    (1696658407, 'Italian Cooking Party', 'Group cooking class for team building', 260.00, 4, 12, 1, 'PUBLIC', 'CONFIRMED', 1696658421, 1696658407, '2023-10-27 18:00:00', '2023-10-27 20:00:00', '2023-10-06 15:30:00+00', NULL, 'bob_explorer', NULL),
    (1696658414, 'Morning Yoga Session', 'Daily yoga for wellness', 30.00, 1, 20, 0, 'PUBLIC', 'PENDING', 1696658428, 1696658414, '2023-10-12 07:00:00', '2023-10-12 08:00:00', '2023-10-07 09:00:00+00', NULL, 'charlie_traveler', NULL)
ON CONFLICT (id) DO NOTHING;

-- 9. Seed experiences (depends on activities and users)
INSERT INTO experiences (id, activity_id, client_id, rating, review, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 1696658400, 1696658407, 5, 'Amazing hike with beautiful views! The guide was very knowledgeable.', '2023-09-15 16:00:00+00', NULL, 'alice_wanderer', NULL),
    (1696658407, 1696658407, 1696658421, 4, 'Great cooking class, learned a lot about Italian cuisine.', '2023-09-20 19:30:00+00', NULL, 'bob_explorer', NULL)
ON CONFLICT (id) DO NOTHING;

-- 10. Seed media_content for experiences (depends on experiences)
INSERT INTO media_content (id, url, type, activity_id, experience_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658477, 'https://example.com/hike-exp1.jpg', 'IMAGE', NULL, 1696658400, '2023-09-15 16:15:00+00', NULL, 'alice_wanderer', NULL),
    (1696658484, 'https://example.com/cooking-exp1.jpg', 'IMAGE', NULL, 1696658407, '2023-09-20 19:45:00+00', NULL, 'bob_explorer', NULL),
    (1696658491, 'https://example.com/hike-video.mp4', 'VIDEO', NULL, 1696658400, '2023-09-15 16:20:00+00', NULL, 'alice_wanderer', NULL)
ON CONFLICT (id) DO NOTHING;

-- 11. Seed payments (depends on plans)
INSERT INTO payments (id, amount, currency, status, transaction_id, plan_id, processed_at)
VALUES
    ('pay_1', 180.00, 'USD', 'SUCCEEDED', 'txn_123456', 1696658400, '2023-10-05 14:30:00+00'),
    ('pay_2', 260.00, 'USD', 'SUCCEEDED', 'txn_789012', 1696658407, '2023-10-06 16:00:00+00'),
    ('pay_3', 30.00, 'USD', 'PENDING', NULL, 1696658414, '2023-10-07 09:15:00+00')
ON CONFLICT (id) DO NOTHING;

-- 12. Seed posts (depends on users and communities)
INSERT INTO posts (id, title, content, author_id, community_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Best Fall Hiking Trails', 'What are your favorite hiking spots for fall foliage this year?', 1696658407, 1696658400, '2023-10-02 09:00:00+00', NULL, 'alice_wanderer', NULL),
    (1696658407, 'Authentic Pizza Recipe', 'Sharing my grandmother''s secret pizza dough recipe!', 1696658421, 1696658407, '2023-10-02 10:30:00+00', NULL, 'bob_explorer', NULL)
ON CONFLICT (id) DO NOTHING;

-- 13. Seed comments (depends on users, plans, and posts)
INSERT INTO comments (id, content, author_id, plan_id, post_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1696658400, 'Looking forward to this hike!', 1696658421, 1696658400, NULL, '2023-10-05 14:15:00+00', NULL, 'bob_explorer', NULL),
    (1696658407, 'Can''t wait to learn how to cook pasta!', 1696658428, 1696658407, NULL, '2023-10-06 15:45:00+00', NULL, 'charlie_traveler', NULL),
    (1696658414, 'I love the Appalachian Trail in October!', 1696658428, NULL, 1696658400, '2023-10-02 10:00:00+00', NULL, 'charlie_traveler', NULL),
    (1696658421, 'Thanks for sharing! Tried it and it''s amazing!', 1696658407, NULL, 1696658407, '2023-10-02 11:30:00+00', NULL, 'alice_wanderer', NULL)
ON CONFLICT (id) DO NOTHING;

-- 14. Seed junction tables that depend on plans and comments
-- Seed plans_comments
INSERT INTO plans_comments (Plan_id, comments_id)
VALUES
    (1696658400, 1696658400),
    (1696658407, 1696658407)
ON CONFLICT (Plan_id, comments_id) DO NOTHING;

-- Seed plans_users (depends on plans and users)
INSERT INTO plans_users (Plan_id, participants_id)
VALUES
    (1696658400, 1696658407), -- Alice in hiking plan
    (1696658400, 1696658421), -- Bob in hiking plan
    (1696658400, 1696658428), -- Charlie in hiking plan
    (1696658407, 1696658421), -- Bob in cooking plan
    (1696658407, 1696658428), -- Charlie in cooking plan
    (1696658414, 1696658428)  -- Charlie in yoga plan
ON CONFLICT (Plan_id, participants_id) DO NOTHING;

-- 15. Seed client_preferences (depends on users and tags)
INSERT INTO client_preferences (Client_id, preferredTagIds)
VALUES
    (1696658407, 1696658400), -- Alice prefers HIKING
    (1696658407, 1696658414), -- Alice prefers OUTDOORS
    (1696658421, 1696658407), -- Bob prefers COOKING
    (1696658421, 1696658421), -- Bob prefers FOOD
    (1696658428, 1696658442), -- Charlie prefers YOGA
    (1696658428, 1696658449)  -- Charlie prefers WELLNESS
;
