using System.Windows;

namespace DesktopApplication
{
    public class User : IUser
    {
        public string username { get; set; }
        public string mail { get; set; }

        public User(string username, string mail="")
        {
            this.username = username;
            this.mail = $"{this.username}@gmail.com";
        }

        public void ShowInfo()
        {
            //MessageBox.Show("Username: " + username + " password: " + password + " mail: " + mail);
            MessageBox.Show($"Username: {this.username} mail: {this.mail}");
        }

        public override string ToString()
        {
            return $"Username: {this.username} mail: {this.mail}";
        }
    }
}
