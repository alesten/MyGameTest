using System;
using UnityEngine;
using System.Collections;
using Assets.API.Exceptions;
using Assets.API.Models;
using UnityEngine.UI;

public class APITest : MonoBehaviour
{

    private ApiController _apiController;

    public Text Response;
    public InputField UserNameInputField;
    public InputField PasswordInputField;


    public APITest()
    {
        _apiController = new ApiController();
    }

    public void TestApi()
    {
        Response.text = null;

        try
        {
            var user = _apiController.AddUser(UserNameInputField.text, PasswordInputField.text);
            Response.text = String.Format("User with username {0} and id {1} was succesfully created", user.UserName, user.Id);
        }
        catch (UserNameAlreadyInUseException e)
        {
            Response.text = "Username is already in use!";
        }
        catch (SomethingWentWrongException)
        {
            Response.text = "Something went wrong. Please try again later";
        } 
    }
}
