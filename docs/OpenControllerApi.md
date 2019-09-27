# OpenControllerApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNewUserUsingPOST**](OpenControllerApi.md#addNewUserUsingPOST) | **POST** /newuser | Creates a new User.


## **addNewUserUsingPOST**

Creates a new User.

The newly created userid will be sent in the response header adds the user role to the new user. Has to be sent username, password, fname, lname, and busname

### Example
```bash
 addNewUserUsingPOST
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

