#############################################################################################
ADMINCONFIGURATION ==
-----------------------
Class to add and configure admins for the application.

Any admin isnt programmable via endpoint (for security reasons)

#############################################################################################
SECURITYCONFIGURATION ==
-----------------------
Class to handle security (mostly security filter) for the application.

Via Spring Security dependecy the application is by default locked down,
and via the SecurityConfig class we can configure whos allowed access through a security filter.

#############################################################################################
SKILIFTCONFIGURATION ==
-----------------------
Class to configure the skilifts for the application.

Also for not by pure accident add another lift. Has to be done server- and developerwise.