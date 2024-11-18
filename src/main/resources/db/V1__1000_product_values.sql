INSERT INTO category (id, name)
VALUES (nextval('category_id_seq'), 'Category 1'),
       (nextval('category_id_seq'), 'Category 2'),
       (nextval('category_id_seq'), 'Category 3'),
       (nextval('category_id_seq'), 'Category 4'),
       (nextval('category_id_seq'), 'Category 5'),
       (nextval('category_id_seq'), 'Category 6'),
       (nextval('category_id_seq'), 'Category 7'),
       (nextval('category_id_seq'), 'Category 8'),
       (nextval('category_id_seq'), 'Category 9'),
       (nextval('category_id_seq'), 'Category 10');

INSERT INTO product (id, category_id, article, name)
SELECT
    nextval('product_id_seq'),
    (SELECT id FROM category ORDER BY random() LIMIT 1),
    'ARTICLE-' || currval('product_id_seq'),
    'Product ' || s.num
FROM generate_series(1, 1000) AS s(num);