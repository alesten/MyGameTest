using Boomlagoon.JSON;

namespace Assets.API
{
    public class JSONConverter
    {
        public static User JsonToUser(string jsonStr)
        {
            var json = JSONObject.Parse(jsonStr);

            var user = new User();

            user.Id = (int) json.GetNumber("id");
            user.UserName = json.GetString("userName");
            user.Password = json.GetString("password");

            return user;
        } 
    }
}