using System.Windows;

namespace DesktopApplication.ViewModel
{
    class User : IUser
    {
        public string username { get; set; }
        public string password { get; set; }
        public string mail { get; set; }

        public User(string username, string password, string mail)
        {
            this.username = username;
            this.password = password;
            this.mail = mail;
        }

        public void ShowInfo()
        {
            //MessageBox.Show("Username: " + username + " password: " + password + " mail: " + mail);
            MessageBox.Show($"Username: {this.username} password: {this.password}  mail: {this.mail}");
        }
    }
}
