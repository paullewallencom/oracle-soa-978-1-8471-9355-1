
@SETLOCAL
@ECHO OFF

REM SET /P ORACLE_HOME=Enter database ORACLE_HOME: 

IF NOT EXIST %ORACLE_HOME%\bin\sqlplus.exe (
    ECHO ERROR: Cannot find sqlplus at %ORACLE_HOME%\bin\sqlplus.exe
    ECHO Please verify that the ORACLE_HOME is set correctly.
    EXIT /B 1
)

%ORACLE_HOME%\bin\sqlplus.exe %1% @CreateUsers.sql

%ORACLE_HOME%\bin\sqlplus.exe obay/obay @CreateSchemas.sql

%ORACLE_HOME%\bin\sqlldr.exe obay/obay control=loader.ctl

PAUSE

@ECHO ON
@ENDLOCAL

