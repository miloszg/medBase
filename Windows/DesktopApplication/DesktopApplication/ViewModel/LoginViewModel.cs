using Ninject;
using System;
using System.Collections.Generic;
using System.Security;
using System.Windows;
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
            //ReceiptPDFCreator pdfCreator = new ReceiptPDFCreator();
            //pdfCreator.GeneratePDF();
            //Application.Current.Shutdown();
            string password = "";
            var passwordContainer = parameter as IPassword;
            if (passwordContainer != null)
            {
                SecureString securePassword = passwordContainer.Passsword;
                password = PasswordHelper.ConvertToUnsecureString(securePassword);
            }
            DatabaseRetriever databaseRetriever = new DatabaseRetriever(IoC.Get<ApplicationWindowViewModel>().databaseManager);

            var doctor = databaseRetriever.GetFromDatabase("lekarz",
                new List<string>()
                {
                    "id","nazwa_uzytkownika", "imie","nazwisko","email"
                },
                $"WHERE nazwa_uzytkownika=\"{this.username}\" AND haslo=\"{password}\"");

            if (doctor != null && doctor.Count == 5)
            {
                /* TODO: Send log of this user */
                var currentWindow = (Application.Current.MainWindow as MainWindow);
                if (currentWindow != null)
                {
                    currentWindow.Hide();
                }
                User user = new User(Int32.Parse(doctor[0]), doctor[1], doctor[2], doctor[3], doctor[4]);
                Application.Current.MainWindow = new ApplicationWindow();
                Application.Current.MainWindow.Show();
                IoC.Get<ApplicationWindowViewModel>().SetDoctor(user);
            }
            else
            {
                MessageBox.Show("Wrong username or password. Try again!");
            }
        }
    }
}
