using System.Collections;
using Assets.API.Exceptions;
using HTTP;
using UnityEngine;

namespace Assets.API.Models
{
    public class ApiController
    {
        public User GetUser(int id)
        {
            var url = ApiConstants.BASE_URL + ApiConstants.USER_URL + "/" + id;
            var www = new WWW(url);

            var user = JSONConverter.JsonToUser(www.text);

            return user;
        }

        public User AddUser(string userName, string password)
        {
            var url = ApiConstants.BASE_URL + ApiConstants.USER_URL;

            Hashtable data = new Hashtable();
            data.Add("userName", userName);
            data.Add("password", password);

            var request = new Request("POST", url, data);
            request.Send();
            while (!request.isDone) { }

            var statusCode = request.response.status;
            if(statusCode == 409)
                throw new UserNameAlreadyInUseException();
            if(statusCode != 201)
                throw new SomethingWentWrongException();

            var json = request.response.Text;

            return JSONConverter.JsonToUser(json);
        }
    }
}