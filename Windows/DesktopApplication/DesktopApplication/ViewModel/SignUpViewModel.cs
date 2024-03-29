﻿using Ninject;
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
        public string name { get; set; }
        public string surname { get; set; }
        public ICommand registerCommand { get; set; }
        public ICommand goBackCommand { get; set; }

        public SignUpViewModel()
        {
            this.registerCommand = new RelayCommandWithParameters((parameter) => Register(parameter));
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
        }
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

            if (databaseEditor.InsertIntoDatabase("lekarz",
                new List<string>()
                {
                    "nazwa_uzytkownika",
                    "imie",
                    "nazwisko",
                    "haslo",
                    "email",
                },
                new List<string>()
                {
                    username,
                    name,
                    surname,
                    password,
                    mail,
                }))
            {
                MessageBox.Show("Insert Succeded!");
            }
        }

        public void GoBackCommand()
        {
            IoC.Kernel.Get<MainWindowViewModel>().CurrentPage = ApplicationPage.Login;
        }
    }
}
