using GalaSoft.MvvmLight.Messaging;
using Ninject;
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
            IoC.Kernel.Get<MainWindowViewModel>().CurrentPage = ApplicationPage.SignUp;
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
                var currentWindow = (Application.Current.MainWindow as MainWindow);
                if (currentWindow != null)
                {
                    currentWindow.Hide();
                }
                User user = new User(username);
                Application.Current.MainWindow = new ApplicationWindow();
                Application.Current.MainWindow.Show();
                IoC.Kernel.Get<ApplicationWindowViewModel>().SetUser(user);
            }
            else
            {
                MessageBox.Show("Wrong username or password. Try again!");
            }
        }
    }
}
