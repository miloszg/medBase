using Renci.SshNet.Messages;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;
using GalaSoft.MvvmLight.Messaging;
using System.Windows;
using Ninject;

namespace DesktopApplication
{
    public class ApplicationWindowViewModel : BaseViewModel
    {
        public ICommand myMedsCommand { get; set; }
        public ICommand searchForMedsCommand { get; set; }
        public ICommand generateQRCommand { get; set; }
        public ICommand notificationCommand { get; set; }
        public ICommand goBackCommand { get; set; }
        public ApplicationPage currentPage { get; set; }

        private User user = null;
        public ApplicationWindowViewModel()
        {
            currentPage = ApplicationPage.MainMenu;
            Messenger.Default.Register<User>(this, (user) => this.user = user);
            this.myMedsCommand = new RelayCommand(() => MyMedsCommand());
            this.searchForMedsCommand = new RelayCommand(() => SearchForMedsCommand());
            this.generateQRCommand= new RelayCommand(() => GenerateQRCommand());
            this.notificationCommand = new RelayCommand(() => NotificationCommand());
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
        }

        private void GoBackCommand()
        {
            this.currentPage = ApplicationPage.MainMenu;
        }

        public void SetUser(User user)
        {
            this.user = user;
        }

        public void MyMedsCommand()
        {
            this.currentPage = ApplicationPage.MyMeds;
        }

        public void SearchForMedsCommand()
        {
            this.currentPage = ApplicationPage.SearchForMeds;
        }

        public void GenerateQRCommand()
        {
            this.currentPage = ApplicationPage.GenerateQRCode;
        }

        public void NotificationCommand()
        {
            this.currentPage = ApplicationPage.Notification;
        }

        private void Logout()
        {
            /* All things to safely logout user */
        }
    }
}
