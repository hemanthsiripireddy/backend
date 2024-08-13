-- Column details
SELECT
    a.attname AS column_name,
    pg_catalog.format_type(a.atttypid, a.atttypmod) AS data_type,
    a.attnotnull AS not_null,
    (SELECT pg_catalog.pg_get_expr(d.adbin, d.adrelid)
     FROM pg_catalog.pg_attrdef d
     WHERE d.adrelid = a.attrelid AND d.adnum = a.attnum AND a.atthasdef) AS default_value,
    col_description(a.attrelid, a.attnum) AS description
FROM
    pg_catalog.pg_attribute a
WHERE
    a.attrelid = 'component'::regclass
    AND a.attnum > 0
    AND NOT a.attisdropped
ORDER BY
    a.attnum;

-- Constraints details
SELECT
    conname AS constraint_name,
    pg_catalog.pg_get_constraintdef(r.oid, true) AS constraint_definition
FROM
    pg_catalog.pg_constraint r
WHERE
    r.conrelid = 'users'::regclass;
