-- Hibernate creates these tables from JPA entities on first run (ddl-auto=update).
CREATE DATABASE IF NOT EXISTS household_consumables;
-- Main tables: items, purchases, expenses, shopping_list_items, consumption_events.
-- For production, replace ddl-auto=update with Flyway/Liquibase migrations.
