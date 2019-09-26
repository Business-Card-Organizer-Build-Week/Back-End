# UserContactControllerApi

All URIs are relative to */api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteContactByIdUsingDELETE**](UserContactControllerApi.md#deleteContactByIdUsingDELETE) | **DELETE** /contact/delete/{usercontactid} | Deletes Contact Based on Id. ***Must be Done by logged in user or Admin***
[**getUserContactsByIdUsingGET**](UserContactControllerApi.md#getUserContactsByIdUsingGET) | **GET** /contact/{id} | LISTS CONTACT BY ID ***LOGGED IN AS A USER***
[**listAllUserContactsUsingGET**](UserContactControllerApi.md#listAllUserContactsUsingGET) | **GET** /contact/all | LISTS ALL CONTACTS ***LOGGED IN AS ADMIN***
[**updateContactUsingPUT**](UserContactControllerApi.md#updateContactUsingPUT) | **PUT** /contact/{contactid} | updateContact


## **deleteContactByIdUsingDELETE**

Deletes Contact Based on Id. ***Must be Done by logged in user or Admin***

### Example
```bash
 deleteContactByIdUsingDELETE usercontactid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **usercontactid** | **integer** | usercontactid |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **getUserContactsByIdUsingGET**

LISTS CONTACT BY ID ***LOGGED IN AS A USER***

### Example
```bash
 getUserContactsByIdUsingGET id=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **integer** | id |

### Return type

[**array[UserContact]**](UserContact.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **listAllUserContactsUsingGET**

LISTS ALL CONTACTS ***LOGGED IN AS ADMIN***

### Example
```bash
 listAllUserContactsUsingGET
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**array[UserContact]**](UserContact.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not Applicable
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

## **updateContactUsingPUT**

updateContact

### Example
```bash
 updateContactUsingPUT contactid=value
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **contactid** | **integer** | contactid |
 **userContact** | [**UserContact**](UserContact.md) | userContact |

### Return type

**map**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

