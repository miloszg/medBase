using System.Collections.Generic;
using System.Security;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace DesktopApplication
{
    public class SignUpViewModel : BaseViewModel
    {
        public string username { get; set; }
        public string mail { get; set; }
        public ICommand connectToDatabaseCommand { get; set; }
        public ICommand registerCommand { get; set; }

        public void Register(object parameter)
        {
            string password = "";
            string repeatedPasssword = "";
            var passwordContainer = parameter as IPassword;
            if(passwordContainer != null)
            {
                SecureString securePassword = passwordContainer.Passsword;
                SecureString secureRepeatedPassword = passwordContainer.RepeatPassword;
                password = PasswordHelper.ConvertToUnsecureString(securePassword);
                repeatedPasssword = PasswordHelper.ConvertToUnsecureString(secureRepeatedPassword);
            }
            if(!repeatedPasssword.Equals(password))
            {
                MessageBox.Show("Wrong repeated password. Type again!",
                                "Wrong repeated password",
                                MessageBoxButton.OK,
                                MessageBoxImage.Information);
                return;
            }
            DatabaseManager databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
            DatabaseEditor databaseEditor = new DatabaseEditor(databaseManager);

            if (databaseEditor.InsertIntoDatabase("pacjent",
                new List<string>()
                {
                    "first_name",
                    "email",
                    "password",
                },
                new List<string>()
                {
                    username,
                    mail,
                    password,
                }))
            {
                MessageBox.Show("Insert Succeded!");
            }
        }
        public SignUpViewModel()
        {
            this.connectToDatabaseCommand = new RelayCommand(() => connectToDatabaseTest());
            this.registerCommand = new RelayCommandWithParameters((parameter) => Register(parameter));
        }

        private void connectToDatabaseTest()
        {
            DatabaseManager databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
            DatabaseEditor databaseEditor = new DatabaseEditor(databaseManager);
        }
    }
}
