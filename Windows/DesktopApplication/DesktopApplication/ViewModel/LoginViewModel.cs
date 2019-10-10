using System.Collections.Generic;
using System.Security;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace DesktopApplication
{
    public class LoginViewModel : BaseViewModel
    {
        public string username { get; set; }
        public ICommand loginCommand { get; set; }
        public ICommand changePageCommand { get; set; }

        public LoginViewModel()
        {
            this.loginCommand = new RelayCommandWithParameters((parameter) => Login(parameter));
            this.changePageCommand = new RelayCommand(() => changePage());
        }

        public void changePage()
        {
            MainWindow.viewModelConnector.CurrentPage = ApplicationPage.SignUp;
        }
        public void Login(object parameter)
        {
            string password = "";
            var passwordContainer = parameter as IPassword;
            if (passwordContainer != null)
            {
                SecureString securePassword = passwordContainer.Passsword;
                password = PasswordHelper.ConvertToUnsecureString(securePassword);
            }
            DatabaseManager databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
            DatabaseRetriever databaseRetriever = new DatabaseRetriever(databaseManager);

            if(databaseRetriever.GetFromDatabase("pacjent",
                new List<string>()
                {
                    "id",
                }, 
                $"WHERE first_name=\"{this.username}\" AND password=\"{password}\"").Count != 0)
            {
                /* TODO: Send log of this user */
                MainWindow.viewModelConnector.CurrentPage = ApplicationPage.MainMenu;
            }
            else
            {
                MessageBox.Show("Wrong username or password. Try again!");
            }
        }
    }
}
