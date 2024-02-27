## 存储过程

### 创建序列优化

增加并发创建异常处理 todo

~~~sql

~~~

### 创建序列

这版有缺陷，并发创建时有几率报错

~~~sql
CREATE OR REPLACE PROCEDURE HR.ADD_SEQ( SEQ_NAME IN VARCHAR2, SEQ_NUM OUT NUMBER, MSG OUT VARCHAR2) IS
    selectSqlStr VARCHAR2(1024);
    createSqlStr VARCHAR2(1024);
    seqName VARCHAR2(128);
BEGIN
    MSG := '';
    seqName := SEQ_NAME;
    selectSqlStr := 'select ' || seqName || '.nextval from dual';
    EXECUTE IMMEDIATE selectSqlStr INTO SEQ_NUM;

EXCEPTION WHEN OTHERS THEN
    MSG := 'call ADD_SEQ error selectSqlStr=' || selectSqlStr || ', SQLCODE=' || SQLCODE() || ', sqlerrm=' || sqlerrm(SQLCODE());
    IF SQLCODE() = -2289 THEN
        BEGIN
            createSqlStr := 'CREATE SEQUENCE ' || seqName || ' START WITH 1 INCREMENT BY 1 CACHE 20 MINVALUE 1 MAXVALUE 33554432 CYCLE NOORDER';
            MSG := MSG || ',seq no exists do create ' || createSqlStr;
            EXECUTE IMMEDIATE createSqlStr;
            EXECUTE IMMEDIATE selectSqlStr INTO SEQ_NUM;
            --createSqlStr := 'grant all on ' || seqName || ' to HR'; 授权操作，这里简化不需要
            --EXECUTE IMMEDIATE createSqlStr;
        END;
    END IF;
END ADD_SEQ;
~~~