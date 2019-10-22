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
        public ICommand medsCommand { get; set; }
        public ICommand patientCommand { get; set; }
        public ICommand goBackCommand { get; set; }
        public ICommand generateQRCommand { get; set; }
        public ApplicationPage currentPage { get; set; }

        public MedsViewModel medsViewModel;

        private DatabaseManager databaseManager;

        private User user = null;
        public ApplicationWindowViewModel()
        {
            currentPage = ApplicationPage.MainMenu;
            Messenger.Default.Register<User>(this, (user) => this.user = user);
            this.medsCommand = new RelayCommand(() => MedsCommand());
            this.patientCommand = new RelayCommand(() => PatientCommand());
            this.generateQRCommand = new RelayCommand(() => GenerateQRCommand());
            this.goBackCommand = new RelayCommand(() => GoBackCommand());
        }

        private void GoBackCommand()
        {
            this.currentPage = ApplicationPage.MainMenu;
            databaseManager = new DatabaseManager("localhost", "leki", "admin", "admin");
        }

        public void SetUser(User user)
        {
            this.user = user;
        }

        public void MedsCommand()
        {
            this.currentPage = ApplicationPage.Meds;
        }

        public void PatientCommand()
        {
            this.currentPage = ApplicationPage.Patient;
        }

        public void GenerateQRCommand()
        {
            this.currentPage = ApplicationPage.GenerateReceipt;
        }

    }
}
