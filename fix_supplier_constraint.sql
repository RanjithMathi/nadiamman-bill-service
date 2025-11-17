-- Fix supplier constraint issue
-- This script should be run before starting the application

-- Step 1: Update existing products to set supplier_id to NULL where it references non-existent suppliers
UPDATE products SET supplier_id = NULL WHERE supplier_id IS NOT NULL AND supplier_id NOT IN (SELECT id FROM suppliers);

-- Step 2: Now the foreign key constraint can be added safely
-- (This will be done automatically by Hibernate when the application starts)

-- Optional: Create a default supplier for existing products (uncomment if needed)
-- INSERT INTO suppliers (name, phone, email, address, gst_number, payment_terms, notes)
-- VALUES ('Default Supplier', '0000000000', 'default@supplier.com', 'Default Address', 'DEFAULT', 'Cash', 'Default supplier for existing products');

-- Optional: Assign default supplier to existing products (uncomment if needed)
-- UPDATE products SET supplier_id = (SELECT id FROM suppliers WHERE name = 'Default Supplier' LIMIT 1) WHERE supplier_id IS NULL;