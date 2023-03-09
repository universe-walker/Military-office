ALTER TABLE IF EXISTS commission_decision
    ADD COLUMN IF NOT EXISTS decision_date DATE DEFAULT now();