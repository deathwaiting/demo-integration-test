-- limiting the range of deleted data is more of over-caution here.
-- It happened before that a misconfiguration caused an organization to run their tests on the production database....
DELETE FROM TRANSACTION_LOG Where id between 1 and 100;
