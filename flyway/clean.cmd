cls

call gradle flywayClean || goto :error

:error
    echo Failed with error#%errorlevel%
    exit /b %errorlevel%