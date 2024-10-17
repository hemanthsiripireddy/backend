CREATE TEMP TABLE temp_table_counts (
    schemaname TEXT,
    tablename TEXT,
    row_count BIGINT
);

DO $$
DECLARE
    row RECORD;
    row_count BIGINT;
BEGIN
    FOR row IN
        SELECT schemaname, tablename
        FROM pg_tables
        WHERE schemaname NOT IN ('pg_catalog', 'information_schema')
    LOOP
        EXECUTE format('SELECT COUNT(*) FROM %I.%I', row.schemaname, row.tablename) INTO row_count;
        EXECUTE format('INSERT INTO temp_table_counts (schemaname, tablename, row_count) VALUES (%L, %L, %L)', row.schemaname, row.tablename, row_count);
    END LOOP;
END $$;

SELECT *
FROM temp_table_counts
ORDER BY row_count DESC;
