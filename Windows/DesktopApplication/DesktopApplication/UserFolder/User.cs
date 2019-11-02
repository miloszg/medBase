using System.Windows;

namespace DesktopApplication
{
    public class User
    {
        public int id { get; private set; }
        public string username { get; private set; }
        public string first_name { get; private set; }
        public string last_name { get; private set; }
        public string sex { get; private set; }
        public string mail { get; private set; }
        public int age { get; private set; }


        public User(int id, string username, string first_name, string last_name, string mail, string sex = "", int age=-1)
        {
            this.id = id;
            this.username = username;
            this.first_name = first_name;
            this.last_name = last_name;
            this.sex = sex;
            this.mail = mail;
            if(age > 0 && age <= 150)
            {
                this.age = age;
            }
        }
    }
}
