cls

call flyway/clean.cmd || goto :error

call flyway/migrate.cmd || goto :error

goto:EOF

:error
    echo Failed with error#%errorlevel%
    exit /b %errorlevel%

