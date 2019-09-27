# UserControllerApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewUserUsingPOST1**](UserControllerApi.md#addNewUserUsingPOST1) | **POST** /users/newuser | Creates a new User
[**deleteUserByIdUsingDELETE**](UserControllerApi.md#deleteUserByIdUsingDELETE) | **DELETE** /users/delete/{id} | Deletes User Based on Id
[**deleteUserContactByIdUsingDELETE**](UserControllerApi.md#deleteUserContactByIdUsingDELETE) | **DELETE** /users/{userid}/contact/{contactid} | Deletes Saved User Contact based on id on Id
[**deleteUserRoleByIdsUsingDELETE**](UserControllerApi.md#deleteUserRoleByIdsUsingDELETE) | **DELETE** /users/user/{userid}/role/{roleid} | Deletes User Role Based on Id
[**getCurrentUserNameUsingGET**](UserControllerApi.md#getCurrentUserNameUsingGET) | **GET** /users/getusername | Return Current User
[**getUserByIdUsingGET**](UserControllerApi.md#getUserByIdUsingGET) | **GET** /users/user/{userId} | Return User Based on Id
[**getUserByNameUsingGET**](UserControllerApi.md#getUserByNameUsingGET) | **GET** /users/name/{userName} | Return User Based on User Name
[**listAllUsersUsingGET**](UserControllerApi.md#listAllUsersUsingGET) | **GET** /users/all | Return All Users
[**postSavedContactsByIdsUsingPOST**](UserControllerApi.md#postSavedContactsByIdsUsingPOST) | **POST** /users/{userid}/contact/{contactid} | Adds a Contact to Saved Contacts based on contactid
[**postUserRoleByIdsUsingPOST**](UserControllerApi.md#postUserRoleByIdsUsingPOST) | **POST** /users/{userid}/role/{roleid} | Adds a Role to a User based on ID
[**updateUserUsingPUT**](UserControllerApi.md#updateUserUsingPUT) | **PUT** /users/user/{id} | Updates A user Based on ID


## **addNewUserUsingPOST1**

Creates a new User

Newly Created user id sent in response header

### Example
```bash
 addNewUserUsingPOST1
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newuser** | [**User**](User.md) | newuser |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteUserByIdUsingDELETE**

Deletes User Based on Id

### Example
```bash
 deleteUserByIdUsingDELETE id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteUserContactByIdUsingDELETE**

Deletes Saved User Contact based on id on Id

### Example
```bash
 deleteUserContactByIdUsingDELETE contactid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **contactid** | **integer** | contactid |
 **userid** | **integer** | userid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **deleteUserRoleByIdsUsingDELETE**

Deletes User Role Based on Id

### Example
```bash
 deleteUserRoleByIdsUsingDELETE roleid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleid** | **integer** | roleid |
 **userid** | **integer** | userid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getCurrentUserNameUsingGET**

Return Current User

### Example
```bash
 getCurrentUserNameUsingGET  authenticated=value  authorities[0].authority=value  Specify as:   Specify as:   Specify as: 
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authenticated** | **boolean** |  | [optional]
 **authorities[0].authority** | **string** |  | [optional]
 **credentials** | [**map[String, string]**](string.md) |  | [optional]
 **details** | [**map[String, string]**](string.md) |  | [optional]
 **principal** | [**map[String, string]**](string.md) |  | [optional]

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getUserByIdUsingGET**

Return User Based on Id

### Example
```bash
 getUserByIdUsingGET userId=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **integer** | userId |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getUserByNameUsingGET**

Return User Based on User Name

### Example
```bash
 getUserByNameUsingGET userName=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userName** | **string** | userName |

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listAllUsersUsingGET**

Return All Users

### Example
```bash
 listAllUsersUsingGET
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**array[User]**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **postSavedContactsByIdsUsingPOST**

Adds a Contact to Saved Contacts based on contactid

### Example
```bash
 postSavedContactsByIdsUsingPOST contactid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **contactid** | **integer** | contactid |
 **userid** | **integer** | userid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **postUserRoleByIdsUsingPOST**

Adds a Role to a User based on ID

### Example
```bash
 postUserRoleByIdsUsingPOST roleid=value userid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **roleid** | **integer** | roleid |
 **userid** | **integer** | userid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateUserUsingPUT**

Updates A user Based on ID

### Example
```bash
 updateUserUsingPUT id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |
 **updateUser** | [**User**](User.md) | updateUser |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

