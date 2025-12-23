

-- ============================================
-- COMPREHENSIVE SEED DATA FOR TRAVEL PLATFORM
-- ============================================
-- Insert order: Tags → Categories → Users → Locations → Activities → Schedules →
--              Experiences → Media → Communities → Posts → Plans → Payments → Comments → Junction Tables
-- ============================================

-- 1. TAGS (independent table)
INSERT INTO tags (id, name, type, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Hiking', 'ACTIVITY_TYPE', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658407, 'Kayaking', 'ACTIVITY_TYPE', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658414, 'Yoga', 'ACTIVITY_TYPE', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658421, 'Cooking', 'ACTIVITY_TYPE', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658428, 'Photography', 'INTEREST', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658435, 'Beginner', 'SKILL_LEVEL', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658442, 'Intermediate', 'SKILL_LEVEL', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658449, 'Advanced', 'SKILL_LEVEL', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658456, 'Wellness', 'INTEREST', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658463, 'Adventure', 'INTEREST', '2024-10-01 09:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 2. CATEGORIES (independent table)
INSERT INTO categories (id, name, hex_color, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Outdoor Adventures', '#4CAF50', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658407, 'Wellness & Health', '#9C27B0', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658414, 'Cultural Experiences', '#FF9800', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658421, 'Food & Drink', '#F44336', '2024-10-01 09:00:00+00', NULL, 'system', NULL),
(1696658428, 'Water Sports', '#2196F3', '2024-10-01 09:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 3. USERS (independent table)
INSERT INTO users (id, user_type, user_name, email, password_hash, first_name, last_name, business_name, logo, tax_id, created_at, updated_at, created_by, updated_by) VALUES
-- Client Users
(1696658400, 'client', 'john_doe', 'john.doe@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', 'John', 'Doe', NULL, NULL, NULL, '2024-10-01 10:00:00+00', NULL, 'system', NULL),
(1696658407, 'client', 'jane_smith', 'jane.smith@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', 'Jane', 'Smith', NULL, NULL, NULL, '2024-10-01 10:00:00+00', NULL, 'system', NULL),
(1696658414, 'client', 'mike_jones', 'mike.jones@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', 'Mike', 'Jones', NULL, NULL, NULL, '2024-10-01 10:00:00+00', NULL, 'system', NULL),
(1696658421, 'client', 'sara_wilson', 'sara.wilson@email.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', 'Sara', 'Wilson', NULL, NULL, NULL, '2024-10-01 10:00:00+00', NULL, 'system', NULL),
-- Provider Users
(1696658428, 'provider', 'adventure_co', 'contact@adventureco.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', NULL, NULL, 'Adventure Co', 'logo_adventure.png', 'TAX123456', '2024-10-01 10:00:00+00', NULL, 'system', NULL),
(1696658435, 'provider', 'wellness_retreat', 'info@wellnessretreat.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', NULL, NULL, 'Wellness Retreat', 'logo_wellness.png', 'TAX789012', '2024-10-01 10:00:00+00', NULL, 'system', NULL),
(1696658442, 'provider', 'culinary_journeys', 'hello@culinaryjourneys.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIH.FpzpJ7u5ZJ5G/pmiJQpJ2DpVY2W6', NULL, NULL, 'Culinary Journeys', 'logo_culinary.png', 'TAX345678', '2024-10-01 10:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 4. LOCATIONS (independent table)
INSERT INTO locations (id, name, address, latitude, longitude, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Mountain Peak Trailhead', '123 Mountain Road, Alpine Village', 46.9479, 7.4474, '2024-10-01 11:00:00+00', NULL, 'system', NULL),
(1696658407, 'Lake Serenity Shore', '456 Lakeside Drive, Waterside Town', 46.4260, 6.2187, '2024-10-01 11:00:00+00', NULL, 'system', NULL),
(1696658414, 'Yoga Studio Harmony', '789 Peace Street, Zen City', 46.2044, 6.1432, '2024-10-01 11:00:00+00', NULL, 'system', NULL),
(1696658421, 'Culinary Arts Center', '321 Chef Avenue, Gourmet City', 46.1984, 6.1423, '2024-10-01 11:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 5. ACTIVITIES (depends on locations and providers)
INSERT INTO activities (id, name, description, base_price, discount_price, duration, location_id, provider_id, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Sunset Mountain Hike', 'Guided evening hike to watch the sunset from the peak. Includes snacks and photography tips.', 75.00, 65.00, 180, 1696658400, 1696658428, '2024-10-01 12:00:00+00', NULL, 'system', NULL),
(1696658407, 'Morning Lake Kayaking', 'Peaceful morning kayaking session on Lake Serenity. Suitable for beginners.', 55.00, 45.00, 120, 1696658407, 1696658428, '2024-10-01 12:00:00+00', NULL, 'system', NULL),
(1696658414, 'Sunrise Yoga Flow', 'Energizing yoga session at sunrise with meditation and breathing exercises.', 40.00, NULL, 90, 1696658414, 1696658435, '2024-10-01 12:00:00+00', NULL, 'system', NULL),
(1696658421, 'Italian Cooking Masterclass', 'Learn to make authentic pasta and tiramisu from a master chef.', 120.00, 99.00, 240, 1696658421, 1696658442, '2024-10-01 12:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 6. SCHEDULES (depends on activities)
INSERT INTO schedules (id, activity_id, start_date, end_date, start_time, end_time, recurrence, recurring_days, excluded_dates, breaks, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 1696658400, '2024-10-05', NULL, '16:00:00', '19:00:00', 'WEEKLY', 'SAT,SUN', NULL, NULL, '2024-10-01 13:00:00+00', NULL, 'system', NULL),
(1696658407, 1696658407, '2024-10-06', NULL, '08:00:00', '10:00:00', 'DAILY', NULL, '2024-12-25', '30 minute snack break', '2024-10-01 13:00:00+00', NULL, 'system', NULL),
(1696658414, 1696658414, '2024-10-07', '2024-12-31', '06:30:00', '08:00:00', 'CUSTOM_DAYS', 'MON,WED,FRI', NULL, NULL, '2024-10-01 13:00:00+00', NULL, 'system', NULL),
(1696658421, 1696658421, '2024-10-10', NULL, '18:00:00', '22:00:00', 'SINGLE', NULL, NULL, NULL, '2024-10-01 13:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 7. EXPERIENCES (depends on activities and clients)
INSERT INTO experiences (id, activity_id, client_id, rating, review, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 1696658400, 1696658400, 5, 'Absolutely breathtaking views! The guide was knowledgeable and friendly.', '2024-09-15 18:30:00+00', NULL, 'system', NULL),
(1696658407, 1696658400, 1696658407, 4, 'Great hike but a bit challenging for beginners.', '2024-09-20 19:00:00+00', NULL, 'system', NULL),
(1696658414, 1696658407, 1696658414, 5, 'So peaceful on the lake. Would do this every morning if I could!', '2024-09-18 10:30:00+00', NULL, 'system', NULL),
(1696658421, 1696658414, 1696658421, 4, 'Perfect way to start the day. Instructor was excellent.', '2024-09-22 08:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 8. MEDIA_CONTENT (depends on activities and experiences)
INSERT INTO media_content (id, url, type, activity_id, experience_id, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'https://example.com/images/sunset_hike_1.jpg', 'IMAGE', 1696658400, 1696658400, '2024-10-01 14:00:00+00', NULL, 'system', NULL),
(1696658407, 'https://example.com/videos/kayaking_demo.mp4', 'VIDEO', 1696658407, 1696658414, '2024-10-01 14:00:00+00', NULL, 'system', NULL),
(1696658414, 'https://youtube.com/watch?v=abc123yoga', 'YOUTUBE', 1696658414, 1696658421, '2024-10-01 14:00:00+00', NULL, 'system', NULL),
(1696658421, 'https://vimeo.com/123456789', 'VIMEO', 1696658407, 1696658414, '2024-10-01 14:00:00+00', NULL, 'system', NULL),
(1696658428, 'https://example.com/images/cooking_class.jpg', 'IMAGE', 1696658414, 1696658421, '2024-10-01 14:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 9. Update categories with media references (circular dependency resolution)
UPDATE categories SET media_id = 1696658400 WHERE id = 1696658400;
UPDATE categories SET media_id = 1696658407 WHERE id = 1696658407;

-- 10. COMMUNITIES (independent table)
INSERT INTO communities (id, name, description, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Outdoor Enthusiasts', 'For those who love hiking, camping, and all things outdoors!', '2024-10-01 15:00:00+00', NULL, 'system', NULL),
(1696658407, 'Wellness Warriors', 'Share tips, experiences, and support for mental and physical wellbeing.', '2024-10-01 15:00:00+00', NULL, 'system', NULL),
(1696658414, 'Foodie Adventures', 'Discover culinary experiences and share your food journey.', '2024-10-01 15:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 11. POSTS (depends on communities and users)
INSERT INTO posts (id, title, content, author_id, community_id, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Best Hiking Trails for Fall', 'The autumn colors are stunning right now on the Northern Ridge trail...', 1696658400, 1696658400, '2024-10-01 16:00:00+00', NULL, 'system', NULL),
(1696658407, 'Meditation Techniques That Work', 'I''ve been practicing these 5 techniques and my focus has improved significantly...', 1696658407, 1696658407, '2024-10-01 16:00:00+00', NULL, 'system', NULL),
(1696658414, 'Hidden Gem Italian Restaurant', 'Just discovered this amazing family-owned place in the old town...', 1696658414, 1696658414, '2024-10-01 16:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 12. PLANS (depends on schedules and clients)
INSERT INTO plans (id, title, description, final_price, min_participants, max_participants, payment_type, type, status, client_id, schedule_id, start_time, end_time, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'Weekend Hike Group', 'Group hike for weekend adventurers', 65.00, 3, 10, 0, 'PUBLIC', 'CONFIRMED', 1696658400, 1696658400, '2024-10-05 16:00:00', '2024-10-05 19:00:00', '2024-10-01 17:00:00+00', NULL, 'system', NULL),
(1696658407, 'Private Yoga Session', 'One-on-one yoga instruction', 80.00, 1, 1, 1, 'PRIVATE', 'PENDING', 1696658407, 1696658414, '2024-10-08 06:30:00', '2024-10-08 08:00:00', '2024-10-01 17:00:00+00', NULL, 'system', NULL),
(1696658414, 'Cooking Date Night', 'Couples cooking class', 198.00, 2, 2, 0, 'PRIVATE', 'COMPLETED', 1696658414, 1696658421, '2024-10-10 18:00:00', '2024-10-10 22:00:00', '2024-10-01 17:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 13. PAYMENTS (depends on plans)
INSERT INTO payments (id, amount, currency, status, transaction_id, plan_id, processed_at) VALUES
('pay_001', 65.00, 'USD', 'SUCCEEDED', 'txn_001', 1696658400, '2024-10-02 10:30:00+00'),
('pay_002', 80.00, 'USD', 'PENDING', 'txn_002', 1696658407, '2024-10-02 11:00:00+00'),
('pay_003', 198.00, 'USD', 'REFUNDED', 'txn_003', 1696658414, '2024-10-02 11:30:00+00')
ON CONFLICT (id) DO NOTHING;

-- 14. COMMENTS (depends on posts, plans, and users)
INSERT INTO comments (id, content, author_id, plan_id, post_id, created_at, updated_at, created_by, updated_by) VALUES
(1696658400, 'That trail looks amazing! How long is the full loop?', 1696658407, NULL, 1696658400, '2024-10-01 18:00:00+00', NULL, 'system', NULL),
(1696658407, 'I''ll be joining the hike! Looking forward to meeting everyone.', 1696658414, 1696658400, NULL, '2024-10-01 18:00:00+00', NULL, 'system', NULL),
(1696658414, 'Can you share the restaurant name? I''d love to try it!', 1696658421, NULL, 1696658414, '2024-10-01 18:00:00+00', NULL, 'system', NULL),
(1696658421, 'The yoga session was transformative!', 1696658400, 1696658407, NULL, '2024-10-01 18:00:00+00', NULL, 'system', NULL)
ON CONFLICT (id) DO NOTHING;

-- 15. JUNCTION TABLES (connect relationships)

-- Activity Categories
INSERT INTO activity_categories (activities_id, categories_id) VALUES
(1696658400, 1696658400), -- Hiking → Outdoor Adventures
(1696658407, 1696658428), -- Kayaking → Water Sports
(1696658414, 1696658407), -- Yoga → Wellness & Health
(1696658421, 1696658421)  -- Cooking → Food & Drink
ON CONFLICT DO NOTHING;

-- Activity Tags
INSERT INTO activity_tags (activities_id, tags_id) VALUES
(1696658400, 1696658400), -- Hiking → Hiking tag
(1696658400, 1696658435), -- Hiking → Beginner tag
(1696658400, 1696658463), -- Hiking → Adventure tag
(1696658407, 1696658407), -- Kayaking → Kayaking tag
(1696658407, 1696658435), -- Kayaking → Beginner tag
(1696658414, 1696658414), -- Yoga → Yoga tag
(1696658414, 1696658456), -- Yoga → Wellness tag
(1696658421, 1696658421), -- Cooking → Cooking tag
(1696658421, 1696658442)  -- Cooking → Intermediate tag
ON CONFLICT DO NOTHING;

-- Client Preferences
INSERT INTO client_preferences (Client_id, preferredTagIds) VALUES
(1696658400, 1696658400), -- John prefers Hiking
(1696658400, 1696658463), -- John prefers Adventure
(1696658407, 1696658414), -- Jane prefers Yoga
(1696658414, 1696658421)  -- Mike prefers Cooking
ON CONFLICT DO NOTHING;

-- Plans Comments
INSERT INTO plans_comments (Plan_id, comments_id) VALUES
(1696658400, 1696658407), -- Group hike comment
(1696658407, 1696658421)  -- Yoga session comment
ON CONFLICT DO NOTHING;

-- Plans Users (participants)
INSERT INTO plans_users (Plan_id, participants_id) VALUES
(1696658400, 1696658400), -- John in hike group
(1696658400, 1696658407), -- Jane in hike group
(1696658400, 1696658414), -- Mike in hike group
(1696658414, 1696658414), -- Mike in cooking class
(1696658414, 1696658421)  -- Sara in cooking class
ON CONFLICT DO NOTHING;

-- ============================================
-- VERIFICATION QUERIES
-- ============================================
