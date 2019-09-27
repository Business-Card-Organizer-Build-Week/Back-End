# Backend for Business Card Organizer Bash client

## Overview
This is a Bash client script for accessing Backend for Business Card Organizer service.

The script uses cURL underneath for making all REST calls.

## Usage

```shell
# Make sure the script has executable rights
$ chmod u+x 

# Print the list of operations available on the service
$ ./ -h

# Print the service description
$ ./ --about

# Print detailed information about specific operation
$ ./ <operationId> -h

# Make GET request
./ --host http://<hostname>:<port> --accept xml <operationId> <queryParam1>=<value1> <header_key1>:<header_value2>

# Make GET request using arbitrary curl options (must be passed before <operationId>) to an SSL service using username:password
 -k -sS --tlsv1.2 --host https://<hostname> -u <user>:<password> --accept xml <operationId> <queryParam1>=<value1> <header_key1>:<header_value2>

# Make POST request
$ echo '<body_content>' |  --host <hostname> --content-type json <operationId> -

# Make POST request with simple JSON content, e.g.:
# {
#   "key1": "value1",
#   "key2": "value2",
#   "key3": 23
# }
$ echo '<body_content>' |  --host <hostname> --content-type json <operationId> key1==value1 key2=value2 key3:=23 -

# Preview the cURL command without actually executing it
$  --host http://<hostname>:<port> --dry-run <operationid>

```

## Docker image
You can easily create a Docker image containing a preconfigured environment
for using the REST Bash client including working autocompletion and short
welcome message with basic instructions, using the generated Dockerfile:

```shell
docker build -t my-rest-client .
docker run -it my-rest-client
```

By default you will be logged into a Zsh environment which has much more
advanced auto completion, but you can switch to Bash, where basic autocompletion
is also available.

## Shell completion

### Bash
The generated bash-completion script can be either directly loaded to the current Bash session using:

```shell
source .bash-completion
```

Alternatively, the script can be copied to the `/etc/bash-completion.d` (or on OSX with Homebrew to `/usr/local/etc/bash-completion.d`):

```shell
sudo cp .bash-completion /etc/bash-completion.d/
```

#### OS X
On OSX you might need to install bash-completion using Homebrew:
```shell
brew install bash-completion
```
and add the following to the `~/.bashrc`:

```shell
if [ -f $(brew --prefix)/etc/bash_completion ]; then
  . $(brew --prefix)/etc/bash_completion
fi
```

### Zsh
In Zsh, the generated `_` Zsh completion file must be copied to one of the folders under `$FPATH` variable.


## Documentation for API Endpoints

All URIs are relative to */api*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*LogoutControllerApi* | [**logoutUsingGET**](docs/LogoutControllerApi.md#logoutusingget) | **GET** /logout | REVOKES TOKEN
*OpenControllerApi* | [**addNewUserUsingPOST**](docs/OpenControllerApi.md#addnewuserusingpost) | **POST** /newuser | Creates a new User.
*UserContactControllerApi* | [**deleteContactByIdUsingDELETE**](docs/UserContactControllerApi.md#deletecontactbyidusingdelete) | **DELETE** /contact/delete/{usercontactid} | Deletes Contact Based on Id. ***Must be Done by logged in user or Admin***
*UserContactControllerApi* | [**getUserContactsByIdUsingGET**](docs/UserContactControllerApi.md#getusercontactsbyidusingget) | **GET** /contact/{id} | LISTS CONTACT BY ID ***LOGGED IN AS A USER***
*UserContactControllerApi* | [**listAllUserContactsUsingGET**](docs/UserContactControllerApi.md#listallusercontactsusingget) | **GET** /contact/all | LISTS ALL CONTACTS ***LOGGED IN AS ADMIN***
*UserContactControllerApi* | [**updateContactUsingPUT**](docs/UserContactControllerApi.md#updatecontactusingput) | **PUT** /contact/{contactid} | updateContact
*UserControllerApi* | [**addNewUserUsingPOST1**](docs/UserControllerApi.md#addnewuserusingpost1) | **POST** /users/newuser | Creates a new User
*UserControllerApi* | [**deleteUserByIdUsingDELETE**](docs/UserControllerApi.md#deleteuserbyidusingdelete) | **DELETE** /users/delete/{id} | Deletes User Based on Id
*UserControllerApi* | [**deleteUserContactByIdUsingDELETE**](docs/UserControllerApi.md#deleteusercontactbyidusingdelete) | **DELETE** /users/{userid}/contact/{contactid} | Deletes Saved User Contact based on id on Id
*UserControllerApi* | [**deleteUserRoleByIdsUsingDELETE**](docs/UserControllerApi.md#deleteuserrolebyidsusingdelete) | **DELETE** /users/user/{userid}/role/{roleid} | Deletes User Role Based on Id
*UserControllerApi* | [**getCurrentUserNameUsingGET**](docs/UserControllerApi.md#getcurrentusernameusingget) | **GET** /users/getusername | Return Current User
*UserControllerApi* | [**getUserByIdUsingGET**](docs/UserControllerApi.md#getuserbyidusingget) | **GET** /users/user/{userId} | Return User Based on Id
*UserControllerApi* | [**getUserByNameUsingGET**](docs/UserControllerApi.md#getuserbynameusingget) | **GET** /users/name/{userName} | Return User Based on User Name
*UserControllerApi* | [**listAllUsersUsingGET**](docs/UserControllerApi.md#listallusersusingget) | **GET** /users/all | Return All Users
*UserControllerApi* | [**postSavedContactsByIdsUsingPOST**](docs/UserControllerApi.md#postsavedcontactsbyidsusingpost) | **POST** /users/{userid}/contact/{contactid} | Adds a Contact to Saved Contacts based on contactid
*UserControllerApi* | [**postUserRoleByIdsUsingPOST**](docs/UserControllerApi.md#postuserrolebyidsusingpost) | **POST** /users/{userid}/role/{roleid} | Adds a Role to a User based on ID
*UserControllerApi* | [**updateUserUsingPUT**](docs/UserControllerApi.md#updateuserusingput) | **PUT** /users/user/{id} | Updates A user Based on ID


## Documentation For Models

 - [ErrorDetail](docs/ErrorDetail.md)
 - [Role](docs/Role.md)
 - [SavedContacts](docs/SavedContacts.md)
 - [User](docs/User.md)
 - [UserContact](docs/UserContact.md)
 - [UserContactType](docs/UserContactType.md)
 - [UserRoles](docs/UserRoles.md)
 - [ValidationError](docs/ValidationError.md)


## Documentation For Authorization

 All endpoints do not require authorization.

